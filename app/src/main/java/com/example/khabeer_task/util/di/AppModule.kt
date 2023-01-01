package com.raminabbasiiii.movies.di

import com.example.room.domain.usecase.Employee_Usecase
import com.example.room.domain.usecase.Login_UseCase

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.raminabbasiiii.movies.data.data_source.RemoteDataSource
import com.raminabbasiiii.movies.data.data_source.RemoteDataSourceImplementer
import com.raminabbasiiii.movies.data.network.Api
import com.raminabbasiiii.movies.domain.repository.Repository
import com.raminabbasiiii.movies.data.repository.MovieRepositoryImpl
import com.raminabbasiiii.movies.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(gsonBuilder: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
    }

    @Singleton
    @Provides
    fun provideApiService(retrofitBuilder: Retrofit.Builder): Api {
        return retrofitBuilder
            .build()
            .create(Api::class.java)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(api: Api
    ) = RemoteDataSourceImplementer(api) as RemoteDataSource


    @Singleton
    @Provides
    fun provideMovieRepository(
        remoteDataSource: RemoteDataSource
    ) = MovieRepositoryImpl(remoteDataSource) as Repository


    @Singleton
    @Provides
    fun provideLogin_Usecase(
        repository: Repository
    ) = Login_UseCase(repository)

    @Singleton
    @Provides
    fun provideEmployee_Usecase(
        repository: Repository
    ) = Employee_Usecase(repository)

}