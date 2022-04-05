package com.example.quiz4.data

import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse
import retrofit2.Response


interface DataSource {

    suspend fun getUsers(filters: HashMap<String, String>): Response<List<UserResponse>>
}
