package com.example.lookup.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lookup.DetailActivity
import com.example.lookup.UploadVideoActivity
import com.example.lookup.VideoEntity
import com.example.lookup.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var video: ArrayList<VideoEntity>
    private lateinit var adapter: MainAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Home"

        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        binding.progressBar.visibility = View.VISIBLE
        setRv()

    }

    private fun setRv(){
        video = ArrayList()

        binding.uploadVideos.setOnClickListener {
            val intent = Intent(this@MainActivity, UploadVideoActivity::class.java)
            startActivity(intent)
        }

        binding.rvVideo.layoutManager = LinearLayoutManager(applicationContext)
        adapter = MainAdapter()
        viewModel.loadVideo()
        viewModel.getList().observe(this,{User ->
            if (User!=null){
                adapter.setData(User)
            }
        })
        binding.rvVideo.adapter = adapter
        binding.progressBar.visibility = View.GONE

        adapter.setOnItemClickCallback(object : MainAdapter.OnItemClickCallback {
            override fun onItemClicked(userItems: VideoEntity) {
                super.onItemClicked(userItems)
                Toast.makeText(this@MainActivity, "You pick ${userItems.title}", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_BRUH,userItems.title)
                intent.putExtra(DetailActivity.EXTRA_VIDEO,userItems.videoUrl)
                intent.putExtra(DetailActivity.EXTRA_DATE,userItems.uploadDate)
                intent.putExtra(DetailActivity.EXTRA_STATUS, userItems.status)
                intent.putExtra(DetailActivity.EXTRA_DEEPFAKE, userItems.deepfake)
                startActivity(intent)
            }

        })
    }
}