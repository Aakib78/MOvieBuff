package com.aakib.saifi.moviebuff.di

/**
 * Created by Aakib
 * Date : 21/May/2021
 * Project : MovieBuff
 */

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher
