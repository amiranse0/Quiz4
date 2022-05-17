package ir.mohsenafshar.apps.mkbarchitecture.data.remote.network

import com.example.quiz4.data.remote.model.UserDetail
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    @GET("users")
    suspend fun getUserList(@QueryMap filters: HashMap<String, String> = hashMapOf()): List<UserResponse>

    @POST("users")
    suspend fun createUser(
        @Body userReqBody: UserReqBody
    ): String

    @GET("users/{userId}")
    suspend fun getUserDetails(@Path("userId") userId:String): UserDetail

    @Multipart
    @POST("users/{userName}/image")
    suspend fun sendProfileImage(@Path("userName") userName:String,
                         @Part image: MultipartBody.Part)

}