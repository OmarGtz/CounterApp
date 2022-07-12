package com.example.mycounterapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * CounterEntity
 *
 * @author (c) 2022
 */
@Entity(tableName = "Counter")
data class CounterEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "count") val count: Int
)