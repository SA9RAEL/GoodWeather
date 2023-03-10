package com.example.goodweather.data.network

import com.example.goodweather.data.enums.QueryType
import com.example.goodweather.data.model.WeatherInfoDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface ForecastApiService {

    @GET("v1/forecast")
    fun getTodayForecast(
        @Query("hourly", encoded = true) hourlyUnits: String = QueryType.REQUIRED_PARAMS.title,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String
    ): Single<WeatherInfoDTO>

    @GET("v1/forecast")
    fun getNextSixDaysForecast(
        @Query("hourly", encoded = true) hourlyUnits: String = QueryType.REQUIRED_PARAMS.title,
        @Query("current_weather") currentWeather: String = QueryType.CURRENT_WEATHER.title,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): Single<List<WeatherInfoDTO>>
}