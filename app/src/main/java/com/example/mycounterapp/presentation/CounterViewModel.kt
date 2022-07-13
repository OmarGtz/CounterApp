package com.example.mycounterapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycounterapp.domain.model.Counter
import com.example.mycounterapp.domain.model.CounterResult
import com.example.mycounterapp.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * CounterViewModel
 *
 * @author (c) 2022
 */
@HiltViewModel
class CounterViewModel @Inject constructor(
    val getAllCountersUseCase: GetAllCountersUseCase,
    val saveCounterUseCase: SaveCounterUseCase,
    val incrementCounterUseCase: IncrementCounterUseCase,
    val decrementCounterUseCase: DecrementCounterUseCase,
    val removeCounterUseCase: RemoveCounterUseCase
) : ViewModel() {

    private val _counters: MutableLiveData<List<Counter>> = MutableLiveData()
    val counters: LiveData<List<Counter>> = _counters

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<List<Counter>> = _counters

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    fun getCounters() {
        viewModelScope.launch {
            _loading.value = true
            when (val result = getAllCountersUseCase(Unit)) {
                is CounterResult.Success -> _counters.value = result.data!!
                is CounterResult.Error -> _error.value = result.exception.message
            }
            _loading.value = false
        }
    }

    fun saveCounter(title: String) {
        viewModelScope.launch {
            _loading.value = true
            when (val result = saveCounterUseCase(title)) {
                is CounterResult.Success -> _counters.value = result.data!!
                is CounterResult.Error -> _error.value = result.exception.message
            }
            _loading.value = false
        }
    }

    fun updateCounter(id: String, isIncrement: Boolean) {
        viewModelScope.launch {
            _loading.value = true
            val updatedResult =
                if (isIncrement) incrementCounterUseCase(id) else decrementCounterUseCase(id)
            when (updatedResult) {
                is CounterResult.Success -> _counters.value = updatedResult.data!!
                is CounterResult.Error -> _error.value = updatedResult.exception.message
            }
            _loading.value = false
        }
    }

    fun removeCounter(counter: Counter) {
        viewModelScope.launch {
            _loading.value = true
            when (val result = removeCounterUseCase(counter)) {
                is CounterResult.Success -> _counters.value = result.data!!
                is CounterResult.Error -> _error.value = result.exception.message
            }
            _loading.value = false
        }
    }

}