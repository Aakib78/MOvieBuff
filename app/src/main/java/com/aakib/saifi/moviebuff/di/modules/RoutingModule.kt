package com.aakib.saifi.moviebuff.di.modules

import com.aakib.saifi.moviebuff.ui.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Aakib
 * Date : 21/May/2021
 * Project : MovieBuff
 */

@Module
class RoutingModule () {

    @Provides
    @Singleton
    fun provideRouter(): Router {
        return Router()
    }
}