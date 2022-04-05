package ir.mohsenafshar.apps.mkbarchitecture.data.remote.network

import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    @GET("users")
    suspend fun getUserList(@QueryMap filters: HashMap<String, String> = hashMapOf()): Response<List<UserResponse>>

    @POST("users")
    fun createUser(
        @Body userReqBody: UserReqBody
    ): Call<String>

    @Multipart
    @POST("users/{id}/image")
    fun uploadImage(
        @Path("id") id: String,
        @Part image: MultipartBody.Part
    ): Call<Any>
}