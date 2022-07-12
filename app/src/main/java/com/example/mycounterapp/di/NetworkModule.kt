package com.example.mycounterapp.di

import com.example.mycounterapp.data.remote.CounterApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideCountersApi(): CounterApiService {
        val builder = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3100/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return builder.create(CounterApiService::class.java)
    }
}