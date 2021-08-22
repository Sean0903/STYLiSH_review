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
            //為什麼catalog的每一頁可以Query到不同的產品?

            //因為從Service就預設Query實會依照不同的type去到不同path，所以getProperties(這裡也先預設) 。
            //然後fragment呼叫viewModel.getProperties(type)，這裡也先預設 。
            //真的給值的地方是從PagerAdapter指定不同position會丟不同type，由CatalogFragment(val type: String)接住，
            //最後傳入viewModel.getProperties(type)

            try {
                val result = StylishApi.retrofitService.getCatalogList(type,null)


                _products.value = result.data

                getNextPage = result.data

                Log.d("check1", "newPage.value = ${newPage.value.toString()} ")
                newPage.value = result.nextPaging
                Log.d("check2", "newPage.value = ${newPage.value.toString()} ")

            } catch (e: Exception) {
                Log.i("Demo", "exception=${e.message}")
            }
        }
    }

    fun getNextPage(type: String) {
        coroutineScope.launch {

            try {

                //在前面getProperties的時候newPage.value已經成為1，所以在呼叫getCatalogList時就可以抓到nextPage的商品
                val result = StylishApi.retrofitService.getCatalogList(type, newPage.value.toString())
                Log.d("check3", "newPage.value = ${newPage.value.toString()} ")

                //nextPage的商品
                val getNewPageData = result.data
                Log.d("study1","getNewPageData = $getNewPageData ")

                //在_products.value中加上nextPage的商品，此時product的LiveData同時存有原本的商品跟newPage商品
                //而fragment的observe感應到資料變化，會更新畫面
                _products.value = getNextPage + getNewPageData
                Log.d("study2", "_products.value = ${_products.value} ")

//                newPage.value = result.nextPaging
//                Log.d("study3", "newPage.value = ${newPage.value} ")

            } catch (e: Exception) {
                Log.i("Demo", "exception=${e.message}")
            }
        }
    }

}