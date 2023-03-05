package com.example.goodweather.domain.repository

import com.example.goodweather.data.model.WeatherInfoDTO
import io.reactivex.rxjava3.core.Single

interface WeatherRepository {

    fun getForecast(
        latitude: Double,
        longitude: Double,
        precipitation: String,
        weatherCode: String,
        startDate: String,
        endDate: String
    ): Single<WeatherInfoDTO>

    fun getNextSixDaysForecast(
        latitude: Double,
        longitude: Double,
        precipitation: String,
        weatherCode: String
    ): Single<List<WeatherInfoDTO>>

}