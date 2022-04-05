package com.example.quiz4.ui.detail

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz4.data.remote.model.UserDetail
import com.example.quiz4.data.repo.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await

class DetailViewModel(private val repository: UserRepository) : ViewModel() {

    fun getUserDetail(id: String):LiveData<UserDetail> {
        val detailLiveData = MutableLiveData<UserDetail>()
        viewModelScope.launch(Dispatchers.IO) {
            val detail = repository.getUserDetail(id).await()
            detailLiveData.postValue(detail)
        }
        return detailLiveData
    }
}