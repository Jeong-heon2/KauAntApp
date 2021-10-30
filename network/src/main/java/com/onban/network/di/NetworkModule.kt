package com.onban.network.di

import com.onban.network.api.NewsApi
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesNewsApi(converterFactory: Converter.Factory): NewsApi {
        return Retrofit.Builder()
            .baseUrl("http://3.37.25.179")
            .addConverterFactory(converterFactory)
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun providesConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }
}