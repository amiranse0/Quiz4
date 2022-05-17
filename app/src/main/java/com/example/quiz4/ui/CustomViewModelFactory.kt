package com.example.taskmanager.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quiz4.data.repo.UserRepository
import com.example.quiz4.ui.MainViewModel
import com.example.quiz4.ui.dbui.DBViewModel
import com.example.quiz4.ui.detail.DetailViewModel
import com.example.quiz4.ui.home.HomeViewModel

class CustomViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(userRepository) as T
        }
        else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(userRepository) as T
        }
        else if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(userRepository) as T
        }
        return if(modelClass.isAssignableFrom(DBViewModel::class.java)){
            DBViewModel(userRepository) as T
        }
        else {
            modelClass.newInstance()
        }
    }
}