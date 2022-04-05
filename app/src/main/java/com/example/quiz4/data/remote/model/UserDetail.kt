package com.example.quiz4.data.remote.model

data class UserDetail(
    val _id: String,
    val firstName: String,
    val hobbies: List<String>,
    val image: String,
    val lastName: String,
    val nationalCode: String
)