package com.sean.stylish_review.catalog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.sean.stylish_review.databinding.FragmentCatalogBinding

class CatalogFragment(val type: String): Fragment() {

    lateinit var binding: FragmentCatalogBinding

    private val viewModel: CatalogViewModel by lazy {
        ViewModelProvider(this).get(CatalogViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //只要有下面這一行code，就可以xml的檔案與fragemnt綁定，並將畫面呈現出來
        binding = FragmentCatalogBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        //將xml或者說在fragment本身與viewModel連接在一起
        binding.catalogFragment = viewModel

        val adapter = CatalogAdapter()
        binding.catalogRecyclerview.adapter = adapter

        viewModel.products.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            Log.d("study4","如果頁面滑到底應該會呼叫兩次，因為會觀察到資料第二次更新")
        })

        binding.SwipeRefresh.setOnRefreshListener {
            viewModel.getProperties(type)
            binding.SwipeRefresh.isRefreshing = false
        }

        observeEndOfPage()

        viewModel.getProperties(type)

        return binding.root
    }

    private fun observeEndOfPage() {
        binding.catalogRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollVertically(1) && viewModel.newPage.value != null) {
                    viewModel.getNextPage(type)
                }
            }
        })
    }

}