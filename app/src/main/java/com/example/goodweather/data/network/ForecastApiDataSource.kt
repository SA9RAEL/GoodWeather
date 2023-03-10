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
        startDate: String,
        endDate: String
    ): Single<WeatherInfoDTO> =
        apiService.getTodayForecast(
            latitude = latitude,
            longitude = longitude,
            startDate = startDate,
            endDate = endDate
        )

    fun nextSixDaysForecast(
        latitude: Double,
        longitude: Double
    ): Single<List<WeatherInfoDTO>> =
        apiService.getNextSixDaysForecast(
            latitude = latitude,
            longitude = longitude
        )


}