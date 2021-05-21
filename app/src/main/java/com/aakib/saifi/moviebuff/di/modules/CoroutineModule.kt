package com.aakib.saifi.moviebuff.di.modules

import com.aakib.saifi.moviebuff.di.DefaultDispatcher
import com.aakib.saifi.moviebuff.di.IoDispatcher
import com.aakib.saifi.moviebuff.di.MainDispatcher
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by Aakib
 * Date : 21/May/2021
 * Project : MovieBuff
 */

@Module
object CoroutinesModule {

    @DefaultDispatcher
    @JvmStatic
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @JvmStatic
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @JvmStatic
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}