package com.ej.mvvmtest.ui.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ej.mvvmtest.data.models.Item
import com.ej.mvvmtest.databinding.MainItemBinding
import com.ej.mvvmtest.ui.main.MainFragment

class ItemAdapter(private val fragment: MainFragment) : ListAdapter<Item, ItemAdapter.ViewHolder>(ItemCallback()) {

    lateinit var listener: OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MainItemBinding.inflate(inflater, parent, false)
        listener = fragment
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: MainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                listener.onVideoClick(getItem(adapterPosition))
            }
        }

        private val context: Context = binding.root.context
        fun bind(item: Item) {
            item.snippet?.let { snippet ->
                binding.videoTitleTV.text = snippet.title
                val thumbnailUrl = snippet.thumbnails?.high?.url
                Glide.with(context).load(thumbnailUrl).into(binding.videoThumbnailIV)
            }
        }
    }

    interface OnClickListener {
        fun onVideoClick(videoItem: Item)
    }
}