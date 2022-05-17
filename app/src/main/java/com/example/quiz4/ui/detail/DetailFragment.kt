package com.example.quiz4.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.quiz4.App
import com.example.quiz4.R
import com.example.quiz4.data.Result
import com.example.quiz4.databinding.FragmentDetailBinding
import com.example.taskmanager.ui.home.viewmodel.CustomViewModelFactory
import kotlinx.coroutines.launch

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding

    private val viewModel: DetailViewModel by viewModels(factoryProducer = {
        CustomViewModelFactory((requireActivity().application as App).serviceLocator.repository)
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailBinding.bind(view)

        showInfo()

        val id = arguments?.getString("detail") ?: ""

        viewModel.id.postValue(id)

        viewModel.getUserDetail(id)

    }

    private fun showInfo() {

        lifecycleScope.launch {
            viewModel.userDetailStateFlow.collect {
                when (it) {
                    is Result.Success -> {
                        Glide.with(requireContext())
                            .load(it.data.image)
                            .placeholder(R.drawable.ic_baseline_image_24)
                            .into(binding.imageView)

                        binding.detailUserTv.text =
                            "firstname: ${it.data.firstName} \nlastname: ${it.data.lastName} \nid: ${it.data._id} \nhobbies: ${it.data.hobbies} \nnational code: ${it.data.nationalCode}"

                    }
                }
            }
        }
    }
}