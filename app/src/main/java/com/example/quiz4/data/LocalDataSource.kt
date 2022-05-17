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

    suspend fun deleteUser(id: String){
        val userList = userDao.findWithId(id)
        if (userList.size == 1){
            userDao.deleteUser(userList.first())
        }
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }
}