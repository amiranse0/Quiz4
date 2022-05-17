package ir.mohsenafshar.apps.mkbarchitecture.di

import android.app.Application
import com.example.quiz4.data.LocalDataSource
import com.example.quiz4.data.RemoteDataSource
import com.example.quiz4.data.local.AppDataBase
import com.example.quiz4.data.repo.UserRepository
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.network.RetrofitApiProvider

class ServiceLocator(application: Application) {
    private val remoteDataSource = RemoteDataSource(RetrofitApiProvider.userApi)

    private val localDataSource = LocalDataSource(
        AppDataBase.getDatabase(application).getUserDao()
    )

    val repository = UserRepository(remoteDataSource, localDataSource)
}