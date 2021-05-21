package com.aakib.saifi.moviebuff.di.modules


import android.app.Application
import com.aakib.saifi.moviebuff.BuildConfig
import com.aakib.saifi.moviebuff.data.config.BASE_URL
import com.aakib.saifi.moviebuff.data.config.IO_TIMEOUT
import com.aakib.saifi.moviebuff.data.config.cacheSize
import com.aakib.saifi.moviebuff.data.remote.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Aakib
 * Date : 21/May/2021
 * Project : MovieBuff
 */

@Module
class NetworkModule(private val application: Application) {


    @Provides
    @Singleton
    fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }


    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        val cache = Cache(application.cacheDir, cacheSize.toLong())
        // set your desired log level
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(IO_TIMEOUT, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG)
            client.addNetworkInterceptor{chain->
                val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale \
                val request = chain.request()
                    .newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .build()
                return@addNetworkInterceptor chain.proceed(request)
            }
        return client.build()
    }

    @Provides
    @Singleton
    fun providesConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(
        ApiService::class.java)
}