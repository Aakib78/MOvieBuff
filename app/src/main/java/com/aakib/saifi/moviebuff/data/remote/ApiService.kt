package com.aakib.saifi.moviebuff.data.remote

import com.aakib.saifi.moviebuff.data.res_model.MovieRes
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Aakib
 * Date : 21/May/2021
 * Project : MovieBuff
 */

interface ApiService {

    @GET("/3/movie/{category}")
    suspend fun getAllMovies(
        @Path("category") category: String,
        @Query("api_key") apiKey: String?
    ): MovieRes
}