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

class MainViewModel: ViewModel() {
    var videoList = MutableLiveData<List<VideoEntity>>()

    fun loadVideo(){
        val list = ArrayList<VideoEntity>()
        val ref = FirebaseDatabase.getInstance("https://analog-subset-312906-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Videos")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (ds in snapshot.children){
                    val video = ds.getValue(VideoEntity::class.java)
                    list.add(video!!)
                }
                videoList.postValue(list)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun getList(): LiveData<List<VideoEntity>>{
        return videoList
    }
}