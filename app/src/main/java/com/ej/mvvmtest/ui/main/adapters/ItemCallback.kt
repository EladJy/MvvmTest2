package com.ej.mvvmtest.ui.main.adapters

import androidx.recyclerview.widget.DiffUtil
import com.ej.mvvmtest.data.models.Item

class ItemCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id?.videoId == newItem.id?.videoId
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }

}