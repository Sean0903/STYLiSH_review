package com.sean.stylish_review.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sean.stylish_review.network.StylishApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val _product = MutableLiveData<List<DataItem>>()
    val product: LiveData<List<DataItem>>
        get() = _product

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getProperties()
    }

    private fun getProperties() {
        coroutineScope.launch {

            try {

                val result = StylishApi.retrofitService.getMarketingHots()
                _product.value = result.toHomeItems()
                Log.d("seanResult", "result")

            } catch (e: Exception) {
                Log.i("Demo", "exception=${e.message}")
            }
        }
    }
}