package com.sean.stylish_review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sean.stylish_review.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //加上這三行，bottomNavigation才能正常跳轉
        val navView: BottomNavigationView = findViewById(R.id.navigation)
        val navController = findNavController(R.id.home_fragment)
        navView.setupWithNavController(navController)
    }

    fun changeToolbarTitle(Title: String) {
        binding.homeToolbarImage.visibility = GONE
        binding.toolbarText.visibility = VISIBLE
        binding.toolbarText.text = Title
    }
}