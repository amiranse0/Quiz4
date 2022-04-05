package com.example.quiz4.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.quiz4.R
import com.example.quiz4.databinding.FragmentDetailBinding

class DetailFragment:Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailBinding.bind(view)

        val userDetail = arguments?.getString("detail")

        binding.detailUserTv.text = userDetail

    }
}