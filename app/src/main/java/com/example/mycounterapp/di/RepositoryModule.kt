package com.example.mycounterapp.di

import com.example.mycounterapp.data.repository.CounterRepository
import com.example.mycounterapp.data.repository.CounterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * RepositoryDataSource
 *
 * @author (c) 2022, UVI TECH SAPI De CV, KAVAK
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindsRepository(impl: CounterRepositoryImpl): CounterRepository
}