package com.example.quiz4.data.repo


import com.example.quiz4.data.DataSource
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse
import retrofit2.Response

class UserRepository(
    private val localDataSource: DataSource) {

    suspend fun getUsers(filters: HashMap<String, String>):Response<List<UserResponse>>{
        return localDataSource.getUsers(filters)
    }

    suspend fun createUser(userReqBody: UserReqBody){
        localDataSource.createUser(userReqBody)
    }

}
