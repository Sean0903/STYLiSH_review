package com.sean.stylish_review.catalog

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sean.stylish_review.network.Product
import com.sean.stylish_review.network.StylishApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CatalogViewModel : ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _products

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var getNextPage : List<Product> = listOf()

    val newPage = MutableLiveData<Int?>()

    fun getProperties(type: String) {
        coroutineScope.launch {

            try {
                val result = StylishApi.retrofitService.getCatalogList(type,null)
                _products.value = result.data
                getNextPage = result.data
                newPage.value = result.nextPaging
            } catch (e: Exception) {
                Log.i("Demo", "exception=${e.message}")
            }
        }
    }

    fun getNextPage(type: String) {
        coroutineScope.launch {

            try {
                val result = StylishApi.retrofitService.getCatalogList(type, newPage.value.toString())
                val getNewPageData = result.data
                _products.value = getNextPage + getNewPageData
                newPage.value = result.nextPaging
            } catch (e: Exception) {
                Log.i("Demo", "exception=${e.message}")
            }
        }
    }

}