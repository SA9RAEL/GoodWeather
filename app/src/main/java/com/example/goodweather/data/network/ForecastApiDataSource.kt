package com.example.goodweather.data.network

import com.example.goodweather.data.model.WeatherInfoDTO
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ForecastApiDataSource @Inject constructor(
    private val apiService: ForecastApiService
) {
    fun todayForecast(
        latitude: Double,
        longitude: Double,
        ): Single<WeatherInfoDTO> =
        apiService.todayForecast(
            latitude = latitude,
            longitude = longitude
        )

    fun nextSixDaysForecast(
        latitude: Double,
        longitude: Double
    ): Single<List<WeatherInfoDTO>> =
        apiService.sevenDaysForecast(
            latitude = latitude,
            longitude = longitude
        )


}