package com.example.goodweather.domain.repository

import com.example.goodweather.data.model.WeatherInfoDTO
import io.reactivex.rxjava3.core.Single

interface WeatherRepository {

    fun getForecast(latitude: Double, longitude: Double): Single<WeatherInfoDTO>

    fun getPastDaysForecast(latitude: Double, longitude: Double): Single<List<WeatherInfoDTO>>
}