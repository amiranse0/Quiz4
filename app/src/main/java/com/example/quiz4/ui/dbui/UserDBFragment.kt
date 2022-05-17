package com.example.quiz4.ui.dbui

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
import com.example.quiz4.databinding.UsersInDbBinding
import com.example.quiz4.ui.home.MyRecyclerAdaptor
import com.example.taskmanager.ui.home.viewmodel.CustomViewModelFactory
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse
import kotlinx.coroutines.launch

class UserDBFragment : Fragment(R.layout.users_in_db) {

    private lateinit var recyclerAdaptor: MyRecyclerAdaptor

    private var items = mutableListOf<UserResponse>()

    private lateinit var binding: UsersInDbBinding

    lateinit var recyclerView: RecyclerView

    private val viewModel: DBViewModel by viewModels(factoryProducer = {
        CustomViewModelFactory((requireActivity().application as App).serviceLocator.repository)
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = UsersInDbBinding.bind(view)

        draw()

        goToHome()

        removeUserFromDB()

        editUser()

        goToDetail()

    }

    private fun goToDetail() {
        recyclerAdaptor.setToClickOnTask(object : MyRecyclerAdaptor.ClickOnTask {
            override fun clickOnTask(position: Int, view: View?) {
                findNavController().navigate(
                    R.id.action_homeFragment_to_detailFragment,
                    bundleOf("detail" to items[position]._id)
                )
            }
        })
    }

    private fun editUser() {
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

                    val editUserDialog = EditUserDialog(items[swipedPosition])
                    editUserDialog.setGetUser(object : EditUserDialog.GetUser {
                        override fun getUserFromDialog(user: UserReqBody) {
                            viewModel.addNewUser(user)

                            val oldUser = items[swipedPosition]
                            viewModel.addNewUser(
                                UserReqBody(
                                    user.firstName,
                                    user.hobbies,
                                    user.lastName,
                                    user.nationalCode
                                )
                            )
                            viewModel.updateUser(
                                User(
                                oldUser._id,
                                    user.firstName,
                                    user.hobbies.toString(),
                                    "",
                                    user.lastName,
                                    user.nationalCode
                                )
                            )
                        }
                    })

                    editUserDialog.show(parentFragmentManager, "edit")

                }
            }

        ItemTouchHelper(simpleItemTouchCallback).attachToRecyclerView(recyclerView)
    }

    private fun removeUserFromDB() {
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

                    viewModel.removeUser(items[swipedPosition]._id)

                    viewModel.getAllUserInDB()

                    recyclerAdaptor.notifyItemRemoved(swipedPosition)
                    recyclerAdaptor.notifyDataSetChanged()
                }
            }

        ItemTouchHelper(simpleItemTouchCallback).attachToRecyclerView(recyclerView)

    }

    private fun goToHome() {
        binding.floatingActionButtonChangeBd.setOnClickListener {
            findNavController().navigate(R.id.action_userDBFragment_to_homeFragment)
        }
    }

    private fun draw() {

        recyclerView = binding.recDb

        lifecycleScope.launch {
            viewModel.userStateFlow.collect { result ->
                when (result) {
                    is Result.Success -> {
                        items.clear()
                        val users = result.data.map {
                            UserResponse(
                                _id = it._id,
                                firstName = it.firstName,
                                lastName = it.lastName,
                                nationalCode = it.nationalCode,
                                hobbies = it.hobbies.split(" ")
                            )
                        }
                        items.addAll(users)
                        recyclerAdaptor.notifyDataSetChanged()
                    }
                }
            }
        }


        recyclerAdaptor = MyRecyclerAdaptor(items)

        binding.recDb.layoutManager = LinearLayoutManager(requireContext())
        binding.recDb.adapter = recyclerAdaptor
    }
}