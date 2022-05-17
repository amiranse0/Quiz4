package com.example.quiz4.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz4.data.Result
import com.example.quiz4.data.remote.model.UserDetail
import com.example.quiz4.data.repo.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: UserRepository) : ViewModel() {

    private val _userDetailStateFlow = MutableStateFlow<Result<UserDetail>>(Result.Loading)
    val userDetailStateFlow = _userDetailStateFlow

    private val _id = MutableLiveData<String>()
    val id = _id

    init {
        id.value?.let { getUserDetail(it) }
    }

    fun getUserDetail(id: String) {
        viewModelScope.launch {
            repository.getUserDetailFromServer(id).collect{_userDetailStateFlow.value = it}
        }
    }
}