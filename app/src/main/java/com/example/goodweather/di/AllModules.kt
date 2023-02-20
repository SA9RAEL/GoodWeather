package com.example.goodweather.di

import com.example.goodweather.network.ForecastApiService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

const val BASE_URL = "https://api.open-meteo.com/"

@Module
abstract class AllModules {

    companion object {

        @Provides
        @Reusable
        fun httpLoggingInterceptor() = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        @Provides
        @Reusable
        fun provideHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(
                    httpLoggingInterceptor()
                )
                .build()
        }

        @Provides
        @Reusable
        fun provideRetrofit(): ForecastApiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                provideHttpClient()
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ForecastApiService::class.java)
    }

}