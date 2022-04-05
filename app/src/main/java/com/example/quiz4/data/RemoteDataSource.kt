package com.example.quiz4.data

import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.network.UserApi
import retrofit2.Response

class RemoteDataSource(private val userApi: UserApi):DataSource {

    override suspend fun getUsers(filters: HashMap<String, String>): Response<List<UserResponse>> {
        return userApi.getUserList(filters)
    }

    override suspend fun createUser(userReqBody: UserReqBody) {
        userApi.createUser(userReqBody)
    }
}