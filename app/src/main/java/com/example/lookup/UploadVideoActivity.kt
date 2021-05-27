package com.example.lookup

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lookup.databinding.ActivityUploadVideoBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.*
import kotlin.collections.HashMap

@Suppress("DEPRECATION")
class UploadVideoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadVideoBinding
    private lateinit var progressDialog: ProgressDialog

    private var title: String = ""
    private var id: String = title.replace("\\s".toRegex(), "").toLowerCase(Locale.ROOT)
    private var videoUri: Uri? = null
    private var status: String = "onReview"
    private var deepfake: String = ""

    companion object{
        private const val VIDEO_GALLERY_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Upload"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.pickVideo.setOnClickListener {
            videoPickGallery()
        }

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Uploading....")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.uploadVideos.setOnClickListener {
            title = binding.EdtTitle.text.toString().trim()
            when{
                title.isEmpty() -> {
                    Toast.makeText(this, "Title tidak bisa kosong", Toast.LENGTH_SHORT).show()
                }
                videoUri == null -> {
                    Toast.makeText(this, "Pilih video dulu", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    uploadToFirebase()
                }
            }
        }
    }

    private fun videoPickGallery() {
        val intent = Intent()
        intent.type = "video/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(
            Intent.createChooser(intent, "Pilih Video"), VIDEO_GALLERY_CODE
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == RESULT_OK) {
            if (requestCode == VIDEO_GALLERY_CODE) {
                videoUri = data!!.data
                setVideoToVideoView()
            }
        } else {
            Toast.makeText(this, "Gagal ambil video", Toast.LENGTH_SHORT).show()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun setVideoToVideoView() {
        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoView)

        binding.apply {
            videoView.setMediaController(mediaController)
            videoView.setVideoURI(videoUri)
            videoView.requestFocus()
            videoView.setOnPreparedListener {
                videoView.pause()
            }
        }
    }

    private fun uploadToFirebase() {
        progressDialog.show()

        val timestamp ="" + System.currentTimeMillis()

        val filePathAndName = "Videos/video_$timestamp"

        val storageReference = FirebaseStorage.getInstance().getReference(filePathAndName)
        storageReference.putFile(videoUri!!)
            .addOnSuccessListener {
                val uriTask = it.storage.downloadUrl
                while(!uriTask.isSuccessful);
                val downloadUri = uriTask.result
                if (uriTask.isSuccessful){
                    val hashmap = HashMap<String, Any>()
                    hashmap["id"] = title.replace("\\s".toRegex(),"").toLowerCase(Locale.ROOT) + "_$timestamp"
                    hashmap["title"] = title
                    hashmap["uploadDate"] = timestamp
                    hashmap["status"] = status
                    hashmap["deepfake"] = deepfake
                    hashmap["videoUrl"] = "$downloadUri"

                    val dbReference = FirebaseFirestore.getInstance()
                    dbReference.collection("Videos").document(title.replace("\\s".toRegex(),"").toLowerCase(Locale.ROOT) + "_$timestamp")
                            .set(hashmap)
                        .addOnSuccessListener {
                            progressDialog.dismiss()
                            Toast.makeText(this, "Video Uploaded", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            progressDialog.dismiss()
                            Toast.makeText(this,"${it.message}", Toast.LENGTH_SHORT).show()
                        }
                }
            }
            .addOnFailureListener {
                progressDialog.dismiss()
                Toast.makeText(this,"${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}