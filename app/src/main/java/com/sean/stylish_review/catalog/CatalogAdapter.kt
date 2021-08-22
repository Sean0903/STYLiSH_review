package com.sean.stylish_review.catalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sean.stylish_review.databinding.ItemCatalogBinding
import com.sean.stylish_review.network.Product


class CatalogAdapter() : ListAdapter<Product, CatalogAdapter.PagerViewHolder>(DiffCallback()) {

    class PagerViewHolder(var binding: ItemCatalogBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            //呼叫ItemCatalog並指向Product這個data class
            binding.product = product
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        return PagerViewHolder(ItemCatalogBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        //呼叫ViewHolder的bind方法，將資料與對應位置的ViewHolder進行綁定
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}

//val Header = 0
//
//sealed class CatalogData {
//    data class CatalogProduct(val name: String, val price:  String): CatalogData()
//}
//
//class CatalogAdapter() : ListAdapter<CatalogData, RecyclerView.ViewHolder>(DiffCallback()) {
//
//    override fun getItemViewType(position: Int): Int {
//        return when (getItem(position)) {
//            is CatalogData.CatalogProduct -> Header
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        return when (viewType) {
//            Header -> CatalogViewHolder(ItemCatalogBinding.inflate(layoutInflater, parent, false))
//            else -> CatalogViewHolder(ItemCatalogBinding.inflate(layoutInflater, parent, false))
//        }
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        when (holder) {
//            is CatalogViewHolder -> {
//                val data = getItem(position) as CatalogData.CatalogProduct
//                holder.bind(data.name, data.price)
//            }
//        }
//    }
//
//
//    class CatalogViewHolder(private var binding: ItemCatalogBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(clothName: String, clothPrice: String) {
//            binding.productNameText.text = clothName
//            binding.productPriceText.text = clothPrice
//            binding.executePendingBindings()
//        }
//    }
//
//    class DiffCallback : DiffUtil.ItemCallback<CatalogData>() {
//        override fun areItemsTheSame(oldItem: CatalogData, newItem: CatalogData): Boolean {
//            return oldItem == newItem
//        }
//
//        override fun areContentsTheSame(oldItem: CatalogData, newItem: CatalogData): Boolean {
//            return oldItem == newItem
//        }
//
//    }
//
//}