package com.example.quiz4.data.local

import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = false)
    val _id: String,
    val firstName: String,
    val hobbies: String,
    val image: String,
    val lastName: String,
    val nationalCode: String
)