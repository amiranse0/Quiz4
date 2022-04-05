package com.example.quiz4.data.repo


import com.example.quiz4.data.DataSource
import com.example.quiz4.data.remote.model.UserDetail
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse
import retrofit2.Call
import retrofit2.Response

class UserRepository(
    private val remoteDataSource: DataSource) {

    suspend fun getUsers(filters: HashMap<String, String>):Response<List<UserResponse>>{
        return remoteDataSource.getUsers(filters)
    }

    suspend fun createUser(userReqBody: UserReqBody){
        remoteDataSource.createUser(userReqBody)
    }

    suspend fun getUserDetail(id:String):Call<UserDetail>{
        return remoteDataSource.getUserDetail(id)
    }

}
