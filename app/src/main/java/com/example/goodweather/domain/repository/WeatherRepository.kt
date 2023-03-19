package com.example.goodweather.domain.repository

import com.example.goodweather.data.model.WeatherInfoDTO
import com.example.goodweather.presentation.viewmodel.model.Weather
import io.reactivex.rxjava3.core.Single

interface WeatherRepository {

    fun getForecast(isGranted: Boolean): Single<Weather>

    fun getNextSixDaysForecast(): Single<List<WeatherInfoDTO>>

}