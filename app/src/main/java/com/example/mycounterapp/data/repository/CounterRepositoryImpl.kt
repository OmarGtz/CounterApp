package com.example.mycounterapp.data.repository

import android.util.Log
import com.example.mycounterapp.data.local.CounterEntity
import com.example.mycounterapp.data.local.CounterLocalDataSource
import com.example.mycounterapp.data.model.ApiEmptyResponse
import com.example.mycounterapp.data.model.ApiErrorResponse
import com.example.mycounterapp.data.model.ApiSuccessResponse
import com.example.mycounterapp.data.model.CounterDto
import com.example.mycounterapp.data.remote.CounterRemoteDataSource
import com.example.mycounterapp.domain.model.Counter
import com.example.mycounterapp.domain.error.EmptyCountersError
import javax.inject.Inject

/**
 * CounterRepositoryImpl
 *
 * @author (c) 2022, UVI TECH SAPI De CV, KAVAK
 */
class CounterRepositoryImpl @Inject constructor(
    private val localDataSource: CounterLocalDataSource,
    private val remoteDataSource: CounterRemoteDataSource
) : CounterRepository {
    override suspend fun getCounters(): List<Counter> {
        return fetchTasksFromRemoteOrLocal()
    }

    private suspend fun fetchTasksFromRemoteOrLocal(): List<Counter> {
        // Remote first
        when (val remoteCounters = remoteDataSource.getCounters()) {
            is ApiErrorResponse -> Log.e("RemoteError", "Remote data source fetch failed")
            is ApiEmptyResponse -> {
                throw EmptyCountersError()
            }
            is ApiSuccessResponse -> {
                refreshLocalDataSource(remoteCounters.body)
                return remoteCounters.body.map { Counter(it.id, it.title, it.count) }
            }
        }

        // Local if remote fails
        val localCounters = localDataSource.getCounters()
        return localCounters.map { Counter(it.id, it.title, it.count) }
    }

    override suspend fun saveCounter(title: String): List<Counter> {
        when (val newCounters = remoteDataSource.addCounter(title)) {
            is ApiErrorResponse -> throw IllegalStateException("Not save counter")
            is ApiEmptyResponse -> throw EmptyCountersError()
            is ApiSuccessResponse -> {
                val newCounter = newCounters.body.find { it.title == title }
                newCounter?.let {
                    localDataSource.addCounter(CounterEntity(it.id, it.title, it.count))
                    return newCounters.body.map { counterDto ->
                        Counter(
                            counterDto.id,
                            counterDto.title,
                            counterDto.count
                        )
                    }
                }
                throw IllegalStateException("Not save new counter")
            }
        }
    }

    override suspend fun incrementCounter(id: String): List<Counter> {
        when (val newCounters = remoteDataSource.incCounter(id)) {
            is ApiErrorResponse -> throw IllegalStateException("Not save counter")
            is ApiEmptyResponse -> throw EmptyCountersError()
            is ApiSuccessResponse -> {
                val counterUpdated = newCounters.body.find { it.id == id}
                counterUpdated?.let {
                    localDataSource.updateCounter(CounterEntity(it.id, it.title, it.count))
                    return newCounters.body.map { counterDto ->
                        Counter(
                            counterDto.id,
                            counterDto.title,
                            counterDto.count
                        )
                    }
                }
                throw IllegalStateException("Not increment new counter")
            }
        }
    }

    override suspend fun decrementCounter(id: String): List<Counter> {
        when (val newCounters = remoteDataSource.decCounter(id)) {
            is ApiErrorResponse -> throw IllegalStateException("Not save counter in remote")
            is ApiEmptyResponse -> throw EmptyCountersError()
            is ApiSuccessResponse -> {
                val counterUpdated = newCounters.body.find { it.id == id}
                counterUpdated?.let {
                    localDataSource.updateCounter(CounterEntity(it.id, it.title, it.count))
                    return newCounters.body.map { counterDto ->
                        Counter(
                            counterDto.id,
                            counterDto.title,
                            counterDto.count
                        )
                    }
                }
                throw IllegalStateException("Not increment new counter")
            }
        }
    }

    override suspend fun removeCounter(id: String): List<Counter> {
        when (val newCounters = remoteDataSource.removeCounter(id)) {
            is ApiErrorResponse -> throw IllegalStateException("Not save counter")
            is ApiEmptyResponse -> throw EmptyCountersError()
            is ApiSuccessResponse -> {
                val counterRemoved = newCounters.body.find { it.id == id}
                counterRemoved?.let {
                    localDataSource.removeCounter(CounterEntity(it.id, it.title, it.count))
                    return newCounters.body.map { counterDto ->
                        Counter(
                            counterDto.id,
                            counterDto.title,
                            counterDto.count
                        )
                    }
                }
                throw IllegalStateException("Not increment new counter")
            }
        }
    }

    private suspend fun refreshLocalDataSource(counters: List<CounterDto>) {
        counters.map {
            localDataSource.addCounter(CounterEntity(it.id, it.title, it.count))
        }
    }
}