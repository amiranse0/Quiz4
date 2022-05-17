package com.example.quiz4.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz4.data.Result
import com.example.quiz4.data.local.User
import com.example.quiz4.data.repo.UserRepository
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UserRepository):ViewModel() {

    private val _usersStateFlow = MutableStateFlow<Result<List<UserResponse>>>(Result.Loading)
    val userStateFlow = _usersStateFlow

    fun getUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUsersFromServer(hashMapOf()).collect{_usersStateFlow.value = it}
        }
    }

    fun addUserToDB(user: User){
        viewModelScope.launch {
            repository.addUserToDB(user)
        }
    }

}