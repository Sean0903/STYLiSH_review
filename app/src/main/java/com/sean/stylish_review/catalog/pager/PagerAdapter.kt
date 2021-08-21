package com.sean.stylish_review.catalog.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sean.stylish_review.cart.CartFragment
import com.sean.stylish_review.catalog.CatalogFragment
import com.sean.stylish_review.home.HomeFragment


private val ONE = 0
private val TWO = 1
private val THREE = 2

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            ONE -> {
                return CatalogFragment()
            }
            TWO -> {
                return HomeFragment()
            }
            THREE -> {
                return CartFragment()
            }
            else -> {
                return HomeFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            ONE -> {
                return "女裝"
            }
            TWO -> {
                return "男裝"
            }
            THREE -> {
                return "配件"
            }
        }
        return super.getPageTitle(position)
    }

    override fun getCount(): Int {
        return 3
    }
}