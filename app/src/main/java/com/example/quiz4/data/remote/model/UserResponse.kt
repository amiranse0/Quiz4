package ir.mohsenafshar.apps.mkbarchitecture.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")
    val _id: String,
    val firstName: String,
    val lastName: String,
    val nationalCode: String,
    val hobbies: List<String>? = null,
)