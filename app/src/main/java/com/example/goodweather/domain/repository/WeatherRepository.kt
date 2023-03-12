package com.example.goodweather.domain.repository

import com.example.goodweather.data.model.WeatherInfoDTO
import io.reactivex.rxjava3.core.Single

interface WeatherRepository {

    fun getForecast(): Single<WeatherInfoDTO>

    fun getNextSixDaysForecast(): Single<List<WeatherInfoDTO>>

}