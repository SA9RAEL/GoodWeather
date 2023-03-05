package com.example.goodweather.network

import com.example.goodweather.data.enums.QueryType
import com.example.goodweather.data.model.WeatherInfoDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface ForecastApiService {

    @GET("v1/forecast")
    fun getTodayForecast(
        @Query("windspeed_10m") windSpeed: QueryType = QueryType.WIND_SPEED10M,
        @Query("hourly") hourlyUnits: QueryType = QueryType.TEMPERATURE2M,
        @Query("precipitation") precipitation: String,
        @Query("weathercode") weatherCode: String,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String
    ): Single<WeatherInfoDTO>

    @GET("v1/forecast")
    fun getNextSixDaysForecast(
        @Query("windspeed_10m") windSpeed: QueryType = QueryType.WIND_SPEED10M,
        @Query("hourly") hourlyUnits: QueryType = QueryType.TEMPERATURE2M,
        @Query("current_weather") currentWeather: QueryType = QueryType.СURRENT_WEATHER,
        @Query("precipitation") precipitation: String,
        @Query("weathercode") weatherCode: String,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): Single<List<WeatherInfoDTO>>
}