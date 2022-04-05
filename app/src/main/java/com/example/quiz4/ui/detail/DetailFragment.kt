package com.example.quiz4.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.quiz4.App
import com.example.quiz4.R
import com.example.quiz4.databinding.FragmentDetailBinding
import com.example.quiz4.ui.MainViewModel
import com.example.taskmanager.ui.home.viewmodel.CustomViewModelFactory

class DetailFragment:Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding

    private val viewModel: DetailViewModel by viewModels(factoryProducer = {
        CustomViewModelFactory((requireActivity().application as App).serviceLocator.repository)
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailBinding.bind(view)

        showInfo()

    }

    private fun showInfo() {

        val id = arguments?.getString("detail")?:""

        Log.d("TAG", id)

        viewModel.getUserDetail(id).observe(viewLifecycleOwner){
            Glide.with(requireContext())
                .load(it.image)
                .into(binding.imageView)

            binding.detailUserTv.text = "firstname: ${it.firstName} \nlastname: ${it.lastName} \nid: ${it._id} \nhobbies: ${it.hobbies} \nnational code: ${it.nationalCode}"

        }
    }
}