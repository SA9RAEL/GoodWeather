package com.example.goodweather.di

import com.example.goodweather.data.const.BASE_URL
import com.example.goodweather.data.location.LocationDataSource
import com.example.goodweather.data.location.LocationDataSourceImpl
import com.example.goodweather.data.network.ForecastApiService
import com.example.goodweather.data.repository.WeatherRepositoryImpl
import com.example.goodweather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

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
        fun provideHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(
                    httpLoggingInterceptor
                )
                .build()
        }

        @Provides
        @Reusable
        fun provideRetrofit(httpClient: OkHttpClient): ForecastApiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                httpClient
            )
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ForecastApiService::class.java)

    }

    @Binds
    abstract fun bindWeatherRepositoryImpl(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository

    @Binds
    abstract fun bindLocationDataSourceImpl(
        locationDataSourceImpl: LocationDataSourceImpl
    ): LocationDataSource

}