package com.sean.stylish_review.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sean.stylish_review.MainActivity
import com.sean.stylish_review.databinding.FragmentProfileBinding

class ProfileFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProfileBinding.inflate(inflater, container, false)

//        if (activity is MainActivity) {
//            (activity as MainActivity).changeTextTitle("個人")
//        }
        return binding.root
    }
}