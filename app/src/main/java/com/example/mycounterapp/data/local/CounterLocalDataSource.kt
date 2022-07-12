package com.example.mycounterapp.data.local

import com.example.mycounterapp.data.model.ApiResponse
import com.example.mycounterapp.data.model.CounterDto

/**
 * LocalDataSource
 *
 * @author (c) 2022
 */
interface CounterLocalDataSource {
    suspend fun getCounters(): List<CounterEntity>
    suspend fun addCounter(counterEntity: CounterEntity)
    suspend fun updateCounter(counterEntity: CounterEntity)
    suspend fun removeCounter(counterEntity: CounterEntity)
}
