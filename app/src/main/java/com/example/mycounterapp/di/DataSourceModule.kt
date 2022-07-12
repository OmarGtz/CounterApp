package com.example.mycounterapp.di

import com.example.mycounterapp.data.local.CounterDatabase
import com.example.mycounterapp.data.local.CounterLocalDataSource
import com.example.mycounterapp.data.local.DatabaseLocalDataSource
import com.example.mycounterapp.data.remote.CounterApiDataSource
import com.example.mycounterapp.data.remote.CounterApiService
import com.example.mycounterapp.data.remote.CounterRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * DataSourceModule
 *
 * @author (c) 2022
 */
@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(database: CounterDatabase): CounterLocalDataSource {
        return DatabaseLocalDataSource(database.counterDao())
    }

    @Singleton
    @Provides
    fun providesRemoteDataSource(apiService: CounterApiService): CounterRemoteDataSource {
        return CounterApiDataSource(apiService)
    }


}