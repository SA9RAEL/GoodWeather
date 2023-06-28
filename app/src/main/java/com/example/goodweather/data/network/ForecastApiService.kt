package com.example.goodweather.data.network

import com.example.goodweather.data.enums.QueryType
import com.example.goodweather.data.model.WeatherInfoDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface ForecastApiService {

    @GET("v1/forecast")
    fun todayForecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") hourlyUnits: String = QueryType.HOURLY.title,
        @Query("current_weather") currentWeather: String = QueryType.CURRENT_WEATHER.title,
        @Query("forecast_days") forecastDays: String = QueryType.FORECAST_ONE_DAY.title,
        @Query("timezone") timeZone: String = QueryType.TIMEZONE.title
    ): Single<WeatherInfoDTO>

    @GET("v1/forecast")
    fun sevenDaysForecast(
        @Query("hourly", encoded = true) hourlyUnits: String = QueryType.HOURLY.title,
        @Query("current_weather") currentWeather: String = QueryType.CURRENT_WEATHER.title,
        @Query("forecast_day") forecastDay: String = QueryType.FORECAST_SEVEN_DAYS.title,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): Single<List<WeatherInfoDTO>>
}