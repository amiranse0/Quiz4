package com.example.taskmanager.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quiz4.data.repo.UserRepository
import com.example.quiz4.ui.detail.DetailViewModel
import com.example.quiz4.ui.home.HomeViewModel

class CustomViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(userRepository) as T
        }
        return if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            DetailViewModel(userRepository) as T
        }
        else {
            modelClass.newInstance()
        }
    }
}