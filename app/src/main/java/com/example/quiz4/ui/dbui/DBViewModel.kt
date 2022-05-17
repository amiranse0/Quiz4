package com.example.quiz4.ui.dbui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz4.data.Result
import com.example.quiz4.data.local.User
import com.example.quiz4.data.repo.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DBViewModel(private val repository: UserRepository): ViewModel() {

    init {
        getAllUserInDB()
    }

    private val _usersStateFlow = MutableStateFlow<Result<List<User>>>(Result.Loading)
    val userStateFlow = _usersStateFlow

    fun getAllUserInDB(){
        viewModelScope.launch {
            repository.getUsersFromDB().collect {userStateFlow.value = it}
        }
    }

    fun removeUser(id:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(id)
        }
    }
}