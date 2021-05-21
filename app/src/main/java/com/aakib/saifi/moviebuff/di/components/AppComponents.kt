package com.aakib.saifi.moviebuff.di.components

import android.content.Context
import com.aakib.saifi.moviebuff.ui.movielists.MovieListActivity
import com.aakib.saifi.moviebuff.di.modules.*
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Aakib
 * Date : 21/May/2021
 * Project : MovieBuff
 */
@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        CoroutinesModule::class,
        RoutingModule:: class
    ]
)

interface AppComponents {

    fun context(): Context

    fun retrofit(): Retrofit

    fun inject(mainActivity: MovieListActivity)
}