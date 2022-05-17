package com.example.quiz4.data.repo

import com.example.quiz4.data.LocalDataSource
import com.example.quiz4.data.RemoteDataSource
import com.example.quiz4.data.remote.model.UserDetail
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import com.example.quiz4.data.Result
import com.example.quiz4.data.local.User

class UserRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    suspend fun getUsersFromServer(filters: HashMap<String, String>): Flow<Result<List<UserResponse>>> =
        flow {
            try {
                val users = remoteDataSource.getUsers(filters)
                emit(Result.Success(users))
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }

    suspend fun createUser(userReqBody: UserReqBody) {
        remoteDataSource.createUser(userReqBody)
    }

    suspend fun getUserDetailFromServer(id: String): Flow<Result<UserDetail>> = flow {
        try {
            val userInfo = remoteDataSource.getUserDetail(id)
            emit(Result.Success(userInfo))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    suspend fun getUsersFromDB(): Flow<Result<List<User>>> = flow {
        try {
            val users = localDataSource.getUsers()
            emit(Result.Success(users))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    suspend fun addUserToDB(user: User) {
        localDataSource.addUser(user)
    }

    suspend fun getUserDetailFromDB(id: String): Flow<Result<UserDetail>> = flow {
        try {
            val userInfo = remoteDataSource.getUserDetail(id)
            emit(Result.Success(userInfo))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    suspend fun deleteUser(id:String){
        localDataSource.deleteUser(id)
    }

}
