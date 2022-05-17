package com.example.quiz4.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quiz4.App
import com.example.quiz4.R
import com.example.quiz4.data.Result
import com.example.quiz4.databinding.FragmentHomeBinding
import com.example.taskmanager.ui.home.viewmodel.CustomViewModelFactory
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var recyclerAdaptor: MyRecyclerAdaptor

    private var items = mutableListOf<UserResponse>()

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels(factoryProducer = {
        CustomViewModelFactory((requireActivity().application as App).serviceLocator.repository)
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        draw()

        goToDetails()

        viewModel.getUsers()
    }

    private fun goToDetails() {
        recyclerAdaptor.setToClickOnTask(object : MyRecyclerAdaptor.ClickOnTask {
            override fun clickOnTask(position: Int, view: View?) {
                findNavController().navigate(
                    R.id.action_homeFragment_to_detailFragment,
                    bundleOf("detail" to items[position]._id)
                )
            }
        })
    }

    private fun draw() {


        lifecycleScope.launch {
            viewModel.userStateFlow.collect{
                when(it){
                    is Result.Success -> {
                        items.clear()
                        items.addAll(it.data)
                        recyclerAdaptor.notifyDataSetChanged()
                    }
                }
            }
        }

        recyclerAdaptor = MyRecyclerAdaptor(items)

        binding.rec.layoutManager = LinearLayoutManager(requireContext())
        binding.rec.adapter = recyclerAdaptor
    }
}