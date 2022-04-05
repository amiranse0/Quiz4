package com.example.quiz4.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz4.data.repo.UserRepository
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.network.RetrofitApiProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UserRepository):ViewModel() {

    var listUserLiveData = MutableLiveData<List<UserResponse>>()

    fun getUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getUsers(hashMapOf())
            if (response.isSuccessful){
                listUserLiveData.postValue(response.body())
            }
        }
    }

}