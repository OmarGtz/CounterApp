package com.example.mycounterapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CounterEntity::class], version = 1, exportSchema = false)
abstract class CounterDatabase : RoomDatabase() {
    abstract fun counterDao(): CounterDao
}