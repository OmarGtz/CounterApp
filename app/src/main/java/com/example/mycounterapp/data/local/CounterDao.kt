package com.example.mycounterapp.data.local

import androidx.room.*
import com.example.mycounterapp.data.model.CounterDto

/**
 * CounterDao
 *
 * @author (c)
 */
@Dao
interface CounterDao {

    @Query("SELECT * FROM COUNTER")
    fun getAllCounters(): List<CounterEntity>

    @Insert
    fun insertCounter(counter: CounterEntity)

    @Update
    fun updateCounter(counter: CounterEntity): Int

    @Delete
    fun deleteCounter(counter: CounterEntity): Int

}