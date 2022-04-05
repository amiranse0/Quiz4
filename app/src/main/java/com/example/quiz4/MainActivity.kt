package com.example.quiz4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quiz4.databinding.ActivityMainBinding
import com.example.quiz4.ui.adduser.AddDialogFragment
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
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
                    TODO("Not yet implemented")
                }
            })
        }
    }
}