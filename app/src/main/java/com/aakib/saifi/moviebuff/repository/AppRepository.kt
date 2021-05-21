package com.aakib.saifi.moviebuff.repository

import com.aakib.saifi.moviebuff.data.res_model.MovieRes
import com.aakib.saifi.moviebuff.data.res_model.ResultData

/**
 * Created by Aakib
 * Date : 21/May/2021
 * Project : MovieBuff
 */


interface AppRepository {

    suspend fun getAllMovies(category: String, apiKey:String) : ResultData<MovieRes>
}