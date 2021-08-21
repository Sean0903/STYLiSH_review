package com.sean.stylish_review.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sean.stylish_review.databinding.FragmentCatalogBinding

class CatalogFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentCatalogBinding.inflate(inflater, container, false)

        val adapter = CatalogAdapter()
        binding.catalogRecyclerview.adapter = adapter

        val catalogList = mutableListOf<CatalogData>()
        for (i in 0 ..19){
            catalogList.add(CatalogData.CatalogProduct("厚實毛呢格子外套","NT$2140"))
        }

        adapter.submitList(catalogList)

        return binding.root
    }
}