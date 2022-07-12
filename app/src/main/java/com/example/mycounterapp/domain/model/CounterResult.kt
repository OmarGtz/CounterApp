package com.example.mycounterapp.domain.model
/**
 * KavakResult
 *
 * @author (c) 2022
 */
sealed class CounterResult<out R> {
    class Success<out T>(val data: T) : CounterResult<T>()
    class Error(val exception: Throwable): CounterResult<Nothing>()
    object Loading: CounterResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }

}