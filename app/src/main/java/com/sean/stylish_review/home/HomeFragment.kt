package com.sean.stylish_review.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sean.stylish_review.MainActivity
import com.sean.stylish_review.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding

    val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.homeFragment = viewModel

        val adapter = HomeAdapter()
        binding.homeRecyclerview.adapter = adapter

        viewModel.product.observe(viewLifecycleOwner, Observer {
            Log.d("0821", "submitList $it")
                adapter.submitList(it)
        })

        binding.SwipeRefresh.setOnRefreshListener {
            viewModel.getProperties()
            binding.SwipeRefresh.isRefreshing = false
        }

        if (activity is MainActivity) {
            (activity as MainActivity).stylishLogo()
        }

        return binding.root
    }
}