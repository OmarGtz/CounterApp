package com.example.mycounterapp.domain.usecase

import com.example.mycounterapp.data.repository.CounterRepository
import com.example.mycounterapp.di.IoDispatcher
import com.example.mycounterapp.domain.model.Counter
import com.example.mycounterapp.domain.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * RemoveCounterUseCase
 *
 * @author (c) 2022
 */
class RemoveCounterUseCase @Inject constructor(private val repository: CounterRepository, @IoDispatcher dispatcher: CoroutineDispatcher): UseCase<Counter, List<Counter>>(dispatcher) {
    /**
     * Override this to set the code to be executed.
     */
    override suspend fun execute(parameters: Counter): List<Counter> {
        return repository.removeCounter(parameters)
    }

}