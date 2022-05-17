package com.example.quiz4.data

import com.example.quiz4.data.remote.model.UserDetail
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse
import okhttp3.MultipartBody

interface DataSource {

    suspend fun getUsers(filters: HashMap<String, String>): List<UserResponse>

    suspend fun createUser(userReqBody: UserReqBody)

    suspend fun getUserDetail(id:String): UserDetail

    suspend fun uploadImage(id:String, image: MultipartBody.Part)
}
