package com.example.lookup.main

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lookup.VideoEntity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore

class MainViewModel: ViewModel() {
    var videoList = MutableLiveData<List<VideoEntity>>()

    fun loadVideo(){
        val list = ArrayList<VideoEntity>()
        val ref = FirebaseFirestore.getInstance()
        ref.collection("Videos")
            .addSnapshotListener { value, error ->
                list.clear()
                if (error != null){
                    return@addSnapshotListener
                }
                for (ds in value!!){
                    val video = ds.toObject(VideoEntity::class.java)
                    list.add(video)
                }
                videoList.postValue(list)
            }

    }

    fun getList(): LiveData<List<VideoEntity>>{
        return videoList
    }
}