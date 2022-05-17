package com.example.quiz4.data

import com.example.quiz4.data.local.User
import com.example.quiz4.data.local.UserDao

class LocalDataSource(private val userDao: UserDao) {

    suspend fun getUsers(): List<User> {
        return userDao.getAllUsers()
    }

    suspend fun getUserDetail(id: String): User {
        return userDao.getUser(id)
    }

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}