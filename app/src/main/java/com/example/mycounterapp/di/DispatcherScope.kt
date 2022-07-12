package com.example.mycounterapp.di

import javax.inject.Qualifier

/**
 * CoroutinedQualifiers
 *
 * @author (c) 2022
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher
