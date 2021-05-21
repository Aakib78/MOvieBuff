package com.aakib.saifi.moviebuff.data.remote

import com.aakib.saifi.moviebuff.data.res_model.MovieRes
import com.aakib.saifi.moviebuff.data.res_model.ResultData
import com.aakib.saifi.moviebuff.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Created by Aakib
 * Date : 21/May/2021
 * Project : MovieBuff
 */

class RemoteDataSourceImpl(
    private val api: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RemoteDataSource {

    override suspend fun getAllMovies(category: String,apiKey:String): ResultData<MovieRes> = withContext(ioDispatcher){
        val request =
            api.getAllMovies(category, apiKey)
        ResultData.Success(request)
    }

}