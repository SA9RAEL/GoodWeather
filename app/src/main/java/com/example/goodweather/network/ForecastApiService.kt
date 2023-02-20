package com.example.goodweather.network

import com.example.goodweather.data.enums.QueryType
import com.example.goodweather.data.model.WeatherInfo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApiService {

    @GET("v1/forecast")
    fun getCurrentForecast(
        @Query("hourly") hourlyUnits: QueryType = QueryType.TEMPERATURE2M,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): Single<WeatherInfo>

    @GET("v1/forecast")
    fun getLastTenDaysForecast(
        @Query("hourly") hourlyUnits: QueryType = QueryType.TEMPERATURE2M,
        @Query("past_days") pastDays: QueryType = QueryType.PASTDAYS,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): Single<List<WeatherInfo>>
}