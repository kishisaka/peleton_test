package com.ttyl.peloton_test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ttyl.peloton_test.databinding.ItemCellBinding

class ItemAdapter: RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){

    var items: Array<Item>? = arrayOf()

    class ItemViewHolder(binding: ItemCellBinding): RecyclerView.ViewHolder(binding.root) {
        val itemTitle = binding.itemTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        items?.let {
            holder.itemTitle.text = it[position].title
        }
    }

    override fun getItemCount(): Int {
        items?.let{
            return it.size
        }
        return 0
    }
}