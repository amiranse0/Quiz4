package com.example.quiz4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.quiz4.databinding.ActivityMainBinding
import com.example.quiz4.ui.MainViewModel
import com.example.quiz4.ui.adduser.AddDialogFragment
import com.example.quiz4.ui.home.HomeViewModel
import com.example.taskmanager.ui.home.viewmodel.CustomViewModelFactory
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels(factoryProducer = {
        CustomViewModelFactory((this.application as App).serviceLocator.repository)
    })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addNewUser()
    }

    private fun addNewUser() {
        binding.floatingActionButton.setOnClickListener {
            val addDialogFragment = AddDialogFragment()
            addDialogFragment.show(supportFragmentManager, "add")
            addDialogFragment.setGetUser(object : AddDialogFragment.GetUser {
                override fun getUser(user: UserReqBody) {
                    viewModel.createUser(user)
                }
            })
        }
    }
}