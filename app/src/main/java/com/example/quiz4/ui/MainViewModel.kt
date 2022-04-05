package com.example.quiz4.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz4.data.repo.UserRepository
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UserRepository):ViewModel() {

    fun createUser(userReqBody: UserReqBody){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.createUser(userReqBody)
        }
    }
}