package com.aakib.saifi.moviebuff.ui

import android.app.Activity
import android.content.Intent
import com.aakib.saifi.moviebuff.model.Movie
import com.aakib.saifi.moviebuff.ui.moviedetail.MovieDetailActivity
import javax.inject.Singleton

/**
 * Created by Aakib
 * Date : 21/May/2021
 * Project : MovieBuff
 */

@Singleton
class Router {

    val EXTRA_MOVIE_DATA = "EXTRA_MOVIE_DATA"

    fun showDetailActivity(sourceActivity: Activity,movie: Movie){
        val intent=Intent(sourceActivity,MovieDetailActivity::class.java)
        intent.putExtra(EXTRA_MOVIE_DATA,movie)
        sourceActivity.startActivity(intent)
    }
}