package com.example.quiz4.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz4.App
import com.example.quiz4.R
import com.example.quiz4.data.Result
import com.example.quiz4.data.local.User
import com.example.quiz4.databinding.FragmentHomeBinding
import com.example.taskmanager.ui.home.viewmodel.CustomViewModelFactory
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var recyclerAdaptor: MyRecyclerAdaptor

    private var items = mutableListOf<UserResponse>()

    private lateinit var binding: FragmentHomeBinding

    lateinit var recyclerView: RecyclerView

    private val viewModel: HomeViewModel by viewModels(factoryProducer = {
        CustomViewModelFactory((requireActivity().application as App).serviceLocator.repository)
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        draw()

        goToDetails()

        viewModel.getUsers()

        addToDBWithSwaping()

        goToDetailsWithSwaping()

        goToDB()
    }

    private fun goToDB() {
        binding.floatingActionButtonChange.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_userDBFragment)
        }
    }

    private fun goToDetailsWithSwaping() {
        val simpleItemTouchCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val swipedPosition = viewHolder.adapterPosition

                    findNavController().navigate(
                        R.id.action_homeFragment_to_detailFragment,
                        bundleOf("detail" to items[swipedPosition]._id)
                    )

                }
            }

        ItemTouchHelper(simpleItemTouchCallback).attachToRecyclerView(recyclerView)
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

        recyclerView = binding.rec

        lifecycleScope.launch {
            viewModel.userStateFlow.collect {
                when (it) {
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

    private fun addToDBWithSwaping() {
        val simpleItemTouchCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val swipedPosition = viewHolder.adapterPosition

                    val userResponse = items[swipedPosition]
                    val user = User(
                        userResponse._id,
                        userResponse.firstName,
                        userResponse.hobbies.toString(),
                        "",
                        userResponse.lastName,
                        userResponse.nationalCode
                    )

                    viewModel.addUserToDB(user)
                    recyclerAdaptor.notifyDataSetChanged()


                }
            }

        ItemTouchHelper(simpleItemTouchCallback).attachToRecyclerView(recyclerView)
    }
}