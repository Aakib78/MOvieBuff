package com.aakib.saifi.moviebuff.data.res_model

import com.aakib.saifi.moviebuff.model.Movie
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

/**
 * Created by Aakib
 * Date : 21/May/2021
 * Project : MovieBuff
 */

data class MovieRes(
    @SerializedName("page")
    val page: Int,

    @SerializedName("total_results")
    val totalResults: Int,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("results")
    val movieList: ArrayList<Movie>

) : Serializable