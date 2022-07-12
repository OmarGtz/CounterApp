package com.example.mycounterapp.data.remote

import com.example.mycounterapp.data.model.ApiResponse
import com.example.mycounterapp.data.model.CounterDto

/**
 * CounterRemoteDataSource
 *
 * @author (c) 2022, UVI TECH SAPI De CV, KAVAK
 */
interface CounterRemoteDataSource {
    suspend fun getCounters(): ApiResponse<List<CounterDto>>
    suspend fun addCounter(name: String): ApiResponse<List<CounterDto>>
    suspend fun incCounter(id: String): ApiResponse<List<CounterDto>>
    suspend fun decCounter(id: String): ApiResponse<List<CounterDto>>
    suspend fun removeCounter(id: String): ApiResponse<List<CounterDto>>
}