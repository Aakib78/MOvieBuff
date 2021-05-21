package com.aakib.saifi.moviebuff.repository

import com.aakib.saifi.moviebuff.data.remote.ApiService
import com.aakib.saifi.moviebuff.data.remote.RemoteDataSource
import com.aakib.saifi.moviebuff.data.res_model.MovieRes
import com.aakib.saifi.moviebuff.data.res_model.RemoteDataNotFoundException
import com.aakib.saifi.moviebuff.data.res_model.ResultData
import com.aakib.saifi.moviebuff.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Aakib
 * Date : 21/May/2021
 * Project : MovieBuff
 */
class AppRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AppRepository {

    override suspend fun getAllMovies(category: String, apiKey:String): ResultData<MovieRes> {
        return when (val result = remoteDataSource.getAllMovies(category,apiKey)) {
            is ResultData.Success -> {
                val response = result.data
                ResultData.Success(response)
            }
            is ResultData.Error -> {
                ResultData.Error(RemoteDataNotFoundException())
            }
        }
    }
}