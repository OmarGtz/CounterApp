package com.example.mycounterapp.domain.usecase

import com.example.mycounterapp.data.repository.CounterRepository
import com.example.mycounterapp.di.IoDispatcher
import com.example.mycounterapp.domain.model.Counter
import com.example.mycounterapp.domain.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * IncrementCounterUseCase
 *
 * @author (c) 2022
 */
class IncrementCounterUseCase @Inject constructor(val counterRepository: CounterRepository,@IoDispatcher dispatcher: CoroutineDispatcher): UseCase<String, List<Counter>>(dispatcher) {
    /**
     * Override this to set the code to be executed.
     */
    override suspend fun execute(parameters: String): List<Counter> {
        return counterRepository.incrementCounter(parameters)
    }
}