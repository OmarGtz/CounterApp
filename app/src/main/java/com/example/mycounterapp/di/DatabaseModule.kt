package com.example.mycounterapp.di

import android.content.Context
import androidx.room.Room
import com.example.mycounterapp.data.local.CounterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): CounterDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CounterDatabase::class.java,
            "Counters.db"
        ).build()
    }
}