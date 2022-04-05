package ir.mohsenafshar.apps.mkbarchitecture.data.remote.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApiProvider {
    private val interceptor = Interceptor { chain ->
        val oldRequest = chain.request()
        val newRequest = oldRequest.newBuilder()
            .addHeader("Authorization", "Bearer qwersafasdf")
            .build()
        val response = chain.proceed(newRequest)
        response
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://papp.ir/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val userApi = retrofit.create(UserApi::class.java)
}