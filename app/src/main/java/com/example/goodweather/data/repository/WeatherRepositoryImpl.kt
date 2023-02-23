package com.example.goodweather.data.repository

import com.example.goodweather.data.model.WeatherInfoDTO
import com.example.goodweather.domain.repository.WeatherRepository
import com.example.goodweather.network.ForecastApiService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: ForecastApiService
) : WeatherRepository {

    override fun getForecast(latitude: Double, longitude: Double): Single<WeatherInfoDTO> {
        return api.getCurrentForecast(latitude = latitude, longitude = longitude)
    }

    override fun getPastDaysForecast(
        latitude: Double,
        longitude: Double
    ): Single<List<WeatherInfoDTO>> {
        return api.getLastTenDaysForecast(latitude = latitude, longitude = longitude)
    }
}