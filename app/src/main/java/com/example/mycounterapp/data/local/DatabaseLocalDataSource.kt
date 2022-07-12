package com.example.mycounterapp.data.local

import javax.inject.Inject

/**
 * databaseDataSource
 *
 * @author (c) 2022
 */
class DatabaseLocalDataSource @Inject constructor(private val dao: CounterDao) :
    CounterLocalDataSource {
    override suspend fun getCounters(): List<CounterEntity> {
        return dao.getAllCounters()
    }

    override suspend fun addCounter(counterEntity: CounterEntity) {
        dao.insertCounter(counterEntity)
    }

    override suspend fun updateCounter(counterEntity: CounterEntity) {
        dao.updateCounter(counterEntity)
    }

    override suspend fun removeCounter(counterEntity: CounterEntity) {
        dao.deleteCounter(counterEntity)
    }
}