package com.aakib.saifi.moviebuff

import android.app.Application
import com.aakib.saifi.moviebuff.di.components.AppComponents
import com.aakib.saifi.moviebuff.di.components.DaggerAppComponents
import com.aakib.saifi.moviebuff.di.modules.AppModule
import com.aakib.saifi.moviebuff.di.modules.NetworkModule

/**
 * Created by Aakib
 * Date : 21/May/2021
 * Project : MovieBuff
 */
open class MainApplication : Application() {

    companion object {
        lateinit var appComponents: AppComponents
    }

    override fun onCreate() {
        super.onCreate()
        appComponents = initDagger(this)
    }

    private fun initDagger(mainApplication: MainApplication): AppComponents =
        DaggerAppComponents.builder()
            .appModule(AppModule(mainApplication))
            .networkModule(NetworkModule(mainApplication))
            .build()
}