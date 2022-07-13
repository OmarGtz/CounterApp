package com.example.mycounterapp.data.repository

import com.example.mycounterapp.domain.model.Counter

/**
 * CounterRepository
 *
 * @author (c) 2022
 */
interface CounterRepository {

    suspend fun getCounters(): List<Counter>

    suspend fun saveCounter(title: String): List<Counter>

    suspend fun incrementCounter(id: String): List<Counter>

    suspend fun decrementCounter(id: String): List<Counter>

    suspend fun removeCounter(counter: Counter): List<Counter>

}