package com.sean.stylish_review.catalog.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sean.stylish_review.cart.CartFragment
import com.sean.stylish_review.catalog.CatalogFragment
import com.sean.stylish_review.home.HomeFragment


private val one = 0
private val two = 1
private val three = 2

//使用列舉定義成員的類型，也可以保護其中資料使用的狀況
enum class CatalogList(val type: String) {
    Women("women"),
    Men("men"),
    Accessories("accessories")
}

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    //pager數量
    override fun getCount(): Int {
        return 3
    }

    //title名稱
    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            one -> {
                return "女裝"
            }
            two -> {
                return "男裝"
            }
            three -> {
                return "配件"
            }
        }
        return super.getPageTitle(position)
    }

    //從列舉中取出資料，並使type讀取對應的資料??
    override fun getItem(position: Int): Fragment {
        return CatalogFragment(
            when (position) {
                0 -> CatalogList.Women.type
                1 -> CatalogList.Men.type
                2 -> CatalogList.Accessories.type
                else -> CatalogList.Women.type
            }
        )
    }


}


//private val ONE = 0
//private val TWO = 1
//private val THREE = 2
//
//class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
//
//    override fun getItem(position: Int): Fragment {
//        return when (position) {
//            ONE -> CatalogFragment()
//            TWO -> HomeFragment()
//            THREE -> CartFragment()
//            else ->  HomeFragment()
//        }
//    }
//
//    override fun getPageTitle(position: Int): CharSequence? {
//        when (position) {
//            ONE -> {
//                return "女裝"
//            }
//            TWO -> {
//                return "男裝"
//            }
//            THREE -> {
//                return "配件"
//            }
//        }
//        return super.getPageTitle(position)
//    }
//
//    override fun getCount(): Int {
//        return 3
//    }
//}