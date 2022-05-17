package com.example.quiz4.data

import com.example.quiz4.data.remote.model.UserDetail
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.network.UserApi
import okhttp3.MultipartBody

class RemoteDataSource(private val userApi: UserApi):DataSource {

    override suspend fun getUsers(filters: HashMap<String, String>): List<UserResponse> {
        return userApi.getUserList(filters)
    }

    override suspend fun createUser(userReqBody: UserReqBody) {
        userApi.createUser(userReqBody)
    }

    override suspend fun getUserDetail(id: String): UserDetail {
        return userApi.getUserDetails(id)
    }

    override suspend fun uploadImage(id: String, image: MultipartBody.Part) {
        userApi.sendProfileImage(id, image)
    }

}