package com.sean.stylish_review.home

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.sean.stylish_review.databinding.ItemHeaderBinding
import com.sean.stylish_review.databinding.ItemMultipleBinding
import com.sean.stylish_review.databinding.ItemSingleBinding

const val header = 0
const val single = 1
const val multiple = 2

sealed class DataItem {
    data class Header(val title: String) : DataItem()
    data class Single(val product: String, val description: String) : DataItem()
    data class Multiple(val product: String, val description: String) : DataItem()
}

class HomeAdapter() : ListAdapter<DataItem, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> header
            is DataItem.Single -> single
            is DataItem.Multiple -> multiple
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            header -> HeaderViewHolder(ItemHeaderBinding.inflate(layoutInflater, parent, false))
            single -> SingleViewHolder(ItemSingleBinding.inflate(layoutInflater, parent, false))
            else  -> MultipleViewHolder(ItemMultipleBinding.inflate(layoutInflater, parent, false))

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder ->{
                val data = getItem(position) as DataItem.Header
                holder.bind(data.title)
            }
            is SingleViewHolder ->{
                val data = getItem(position) as DataItem.Single
                holder.bind(data.product, data.description)
            }
            is MultipleViewHolder ->{
                val data = getItem(position) as DataItem.Multiple
                holder.bind(data.product, data.description)
            }
        }
    }

    class HeaderViewHolder(var binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.headerTitle.text = item
            binding.executePendingBindings()
        }
    }

    class SingleViewHolder(var binding: ItemSingleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String, description: String) {
            binding.detailTile.text = item
            binding.homeSingleText2.text = description
            binding.executePendingBindings()

        }
    }

    class MultipleViewHolder(var binding: ItemMultipleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String, description: String) {
            binding.homeMultipleText1.text = item
            binding.homeMultipleText2.text = description
            binding.executePendingBindings()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }
}