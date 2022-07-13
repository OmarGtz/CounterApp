package com.example.mycounterapp.data.remote

import com.example.mycounterapp.data.model.CounterDto
import retrofit2.Response
import retrofit2.http.*

/**
 * CounterApiService
 *
 * @author (c) 2022
 */
interface CounterApiService {
    @GET("api/v1/counters")
    suspend fun getCounters(): Response<List<CounterDto>>

    @POST("api/v1/counter")
    suspend fun addCounter(@Body params: Map<String, String>): Response<List<CounterDto>>

    @POST("api/v1/counter/inc")
    suspend fun incCounter(@Body params: Map<String, String>): Response<List<CounterDto>>

    @POST("api/v1/counter/dec")
    suspend fun decCounter(@Body params: Map<String, String>): Response<List<CounterDto>>

    @HTTP(method = "DELETE", path = "api/v1/counter", hasBody = true)
    suspend fun removeCounter(@Body params: Map<String, String>): Response<List<CounterDto>>
}