package com.example.mycounterapp.data.remote

import com.example.mycounterapp.data.model.CounterDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * CounterApiService
 *
 * @author (c) 2022
 */
interface CounterApiService {
    @GET("api/v1/counters")
    suspend fun getCounters(): Response<List<CounterDto>>

    @POST("api/v1/counter")
    suspend fun addCounter(@Body params: Map<String, Any>): Response<List<CounterDto>>

    @POST("api/v1/counter")
    suspend fun incCounter(@Body params: Map<String, Any>): Response<List<CounterDto>>

    @POST("api/v1/counter")
    suspend fun decCounter(@Body params: Map<String, Any>): Response<List<CounterDto>>

    @DELETE("api/v1/counter")
    suspend fun removeCounter(@Body params: Map<String, Any>): Response<List<CounterDto>>
}