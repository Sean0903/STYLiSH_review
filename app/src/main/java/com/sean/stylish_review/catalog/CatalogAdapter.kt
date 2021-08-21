package com.sean.stylish_review.catalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sean.stylish_review.databinding.ItemCatalogBinding


val Header = 0

sealed class CatalogData {
    data class CatalogProduct(val name: String, val price:  String): CatalogData()
}

class CatalogAdapter() : ListAdapter<CatalogData, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CatalogData.CatalogProduct -> Header
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            Header -> CatalogViewHolder(ItemCatalogBinding.inflate(layoutInflater, parent, false))
            else -> CatalogViewHolder(ItemCatalogBinding.inflate(layoutInflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CatalogViewHolder -> {
                val data = getItem(position) as CatalogData.CatalogProduct
                holder.bind(data.name, data.price)
            }
        }
    }


    class CatalogViewHolder(private var binding: ItemCatalogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clothName: String, clothPrice: String) {
            binding.productNameText.text = clothName
            binding.productPriceText.text = clothPrice
            binding.executePendingBindings()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CatalogData>() {
        override fun areItemsTheSame(oldItem: CatalogData, newItem: CatalogData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CatalogData, newItem: CatalogData): Boolean {
            return oldItem == newItem
        }

    }

}