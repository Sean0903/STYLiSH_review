package com.sean.stylish_review.catalog.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sean.stylish_review.MainActivity
import com.sean.stylish_review.databinding.PagerCatalogBinding

class PagerFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = PagerCatalogBinding.inflate(inflater, container, false)

        //顯示tablayout並輸入的title
        binding.catalogPager.adapter = PagerAdapter(childFragmentManager)
        binding.catalogTab.setupWithViewPager(binding.catalogPager)

        if (activity is MainActivity) {
            (activity as MainActivity).changeToolbarTitle("型錄")
        }

        return binding.root
    }
}