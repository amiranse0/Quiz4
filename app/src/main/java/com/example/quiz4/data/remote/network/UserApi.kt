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
    suspend fun createUser(
        @Body userReqBody: UserReqBody
    ): Response<String>

}