package com.example.quiz4.data.local

import androidx.room.*
import com.example.quiz4.data.remote.model.UserDetail

@Dao
interface UserDao {

    @Query("SELECT * FROM USER")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM USER WHERE USER._id == :id")
    suspend fun getUser(id: String): User

    @Delete
    suspend fun deleteUser(user: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM USER WHERE USER._id == :id")
    suspend fun findWithId(id:String): List<User>

    @Update
    suspend fun updateUser(user: User)

}