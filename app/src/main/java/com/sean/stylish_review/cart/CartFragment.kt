package com.sean.stylish_review.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sean.stylish_review.databinding.FragmentCartBinding
import com.sean.stylish_review.databinding.FragmentProfileBinding

class CartFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCartBinding.inflate(inflater, container, false)

        return binding.root
    }
}
