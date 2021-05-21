package com.aakib.saifi.moviebuff.di.modules

import com.aakib.saifi.moviebuff.data.remote.ApiService
import com.aakib.saifi.moviebuff.data.remote.RemoteDataSourceImpl
import com.aakib.saifi.moviebuff.di.IoDispatcher
import com.aakib.saifi.moviebuff.repository.AppRepositoryImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

/**
 * Created by Aakib
 * Date : 21/May/2021
 * Project : MovieBuff
 */

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideAppRepository(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        api: ApiService
    ): AppRepositoryImpl {
        val remoteDataSource = RemoteDataSourceImpl(api, ioDispatcher)
        return AppRepositoryImpl(remoteDataSource, ioDispatcher)
    }
}