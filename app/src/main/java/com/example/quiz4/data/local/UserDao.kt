package com.example.quiz4.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.quiz4.data.remote.model.UserDetail

@Dao
interface UserDao {

    @Query("SELECT * FROM USER")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM USER WHERE USER._id == :id")
    suspend fun getUser(id: String): User

    @Delete
    suspend fun deleteUser(user: User)

    @Insert
    suspend fun addUser(user: User)

}