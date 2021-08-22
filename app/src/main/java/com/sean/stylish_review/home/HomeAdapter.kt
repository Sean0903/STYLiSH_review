package com.sean.stylish_review.home

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.sean.stylish_review.databinding.ItemHeaderBinding
import com.sean.stylish_review.databinding.ItemMultipleBinding
import com.sean.stylish_review.databinding.ItemSingleBinding
import com.sean.stylish_review.network.Product

const val header = 0
const val single = 1
const val multiple = 2

sealed class DataItem {
    data class Header(val title: String) : DataItem()
    data class Single(val product: Product) : DataItem()
    data class Multiple(val product: Product) : DataItem()
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
            //為每個ViewHolder指定data class
            is HeaderViewHolder ->{
                val data = getItem(position) as DataItem.Header
                holder.bind(data)
            }
            is SingleViewHolder ->{
                val data = getItem(position) as DataItem.Single
                holder.bind(data)
            }
            is MultipleViewHolder ->{
                val data = getItem(position) as DataItem.Multiple
                holder.bind(data)
            }
        }
    }

    //共三個viewHolder對應三個不同組成的recyclerView中的區塊。
    //因為在viewHolder已經跟textView做binding了，所以不用在xml裡面再寫text，也不用建立一個data class裝假資料。
    class HeaderViewHolder(var binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataItem.Header) {
            binding.itemHeader = item
            binding.executePendingBindings()
        }
    }

    class SingleViewHolder(var binding: ItemSingleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataItem.Single) {
            binding.itemSingle = item
            binding.executePendingBindings()
        }
    }

    class MultipleViewHolder(var binding: ItemMultipleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataItem.Multiple) {
            binding.itemMultiple = item
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