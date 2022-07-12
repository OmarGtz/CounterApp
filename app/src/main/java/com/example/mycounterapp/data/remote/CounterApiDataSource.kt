package com.example.mycounterapp.data.remote

import com.example.mycounterapp.data.model.ApiResponse
import com.example.mycounterapp.data.model.CounterDto
import com.example.mycounterapp.data.model.handleResult
import javax.inject.Inject

/**
 * CounterRemoteDataSource
 *
 * @author (c) 2022
 */
class CounterApiDataSource @Inject constructor(private val apiService: CounterApiService) :
    CounterRemoteDataSource {

    override suspend fun getCounters(): ApiResponse<List<CounterDto>> {
        return apiService.getCounters().handleResult()
    }

    override suspend fun addCounter(name: String): ApiResponse<List<CounterDto>> {
        return apiService.addCounter(mapOf(pair = Pair("title", name))).handleResult()
    }

    override suspend fun incCounter(id: String): ApiResponse<List<CounterDto>> {
        return apiService.incCounter(mapOf(pair = Pair("id", id))).handleResult()
    }

    override suspend fun decCounter(id: String): ApiResponse<List<CounterDto>> {
        return apiService.decCounter(mapOf(pair = Pair("id", id))).handleResult()
    }

    override suspend fun removeCounter(id: String): ApiResponse<List<CounterDto>> {
        return apiService.removeCounter(mapOf(pair = Pair("id", id))).handleResult()
    }
}