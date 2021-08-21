package com.sean.stylish_review.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sean.stylish_review.databinding.FragmentCatalogBinding

class CatalogFragment: Fragment() {

    lateinit var binding: FragmentCatalogBinding

    private val viewModel: CatalogViewModel by lazy {
        ViewModelProvider(this).get(CatalogViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentCatalogBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.catalogFragment = viewModel

        val adapter = CatalogAdapter()
        binding.catalogRecyclerview.adapter = adapter

        viewModel.products.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        return binding.root
    }
}