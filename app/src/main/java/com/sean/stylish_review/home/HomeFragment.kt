package com.sean.stylish_review.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

        val dataList = mutableListOf<DataItem>()
        dataList.add(DataItem.Header("2018冬裝搶先看"))
        dataList.add(DataItem.Single("厚實毛呢格子外套","高抗寒素材選用，保暖也時尚有型"))
        dataList.add(DataItem.Multiple("羊毛粗織長版毛衣","保暖又不厚重，輕柔質感新美學"))
        dataList.add(DataItem.Header("2018早冬新品"))
        dataList.add(DataItem.Multiple("霧面框框牛皮帶","精選霧面金屬框，展現百變造型風格"))

        val adapter = HomeAdapter()
        binding.homeRecyclerview.adapter = adapter
        adapter.submitList(dataList)

        return binding.root
    }


}