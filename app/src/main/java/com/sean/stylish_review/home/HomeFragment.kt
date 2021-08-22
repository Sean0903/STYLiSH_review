package com.sean.stylish_review.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sean.stylish_review.MainActivity
import com.sean.stylish_review.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.homeFragment = viewModel

        val adapter = HomeAdapter(HomeAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })
        binding.homeRecyclerview.adapter = adapter

        viewModel.product.observe(viewLifecycleOwner, Observer {
                adapter.submitList(it)
            binding.SwipeRefresh.isRefreshing = false
        })

        binding.SwipeRefresh.setOnRefreshListener {
            viewModel.getProperties()

        }

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }

        })

        if (activity is MainActivity) {
            (activity as MainActivity).stylishLogo()
        }

        return binding.root
    }
}