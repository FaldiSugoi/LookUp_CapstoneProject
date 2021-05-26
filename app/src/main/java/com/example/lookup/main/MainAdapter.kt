package com.example.lookup.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lookup.R
import com.example.lookup.VideoEntity
import com.example.lookup.databinding.ListItemVideoBinding
import java.util.*
import kotlin.collections.ArrayList

class MainAdapter: RecyclerView.Adapter<MainAdapter.HolderVideo>() {
    var videoList = ArrayList<VideoEntity>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setData(item: List<VideoEntity>){
        videoList.clear()
        videoList.addAll(item)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    inner class HolderVideo(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding = ListItemVideoBinding.bind(itemView)

        fun bind(modelVideo: VideoEntity){
            with(itemView){
                binding.titleVideo.text = modelVideo.title
                val timestamp = modelVideo.uploadDate.also { this@HolderVideo.binding.time.text = it }
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = timestamp!!.toLong()
                val formattedDateTime = android.text.format.DateFormat.format("dd/MM/yyyy K:mm a",calendar).toString()
                binding.time.text = formattedDateTime
                binding.tvStatus.text = modelVideo.status

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(modelVideo) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderVideo {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_video, parent, false)
        return HolderVideo(view)
    }

    override fun onBindViewHolder(holder: HolderVideo, position: Int) {
        holder.bind(videoList[position])
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(userItems: VideoEntity){

        }
    }


}