package com.sean.stylish_review.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sean.stylish_review.databinding.FragmentCartBinding
import com.sean.stylish_review.databinding.FragmentCatalogBinding

class CatalogFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCatalogBinding.inflate(inflater, container, false)

        return binding.root
    }
}