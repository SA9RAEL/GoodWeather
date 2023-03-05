package com.example.goodweather.data.repository

import com.example.goodweather.data.model.WeatherInfoDTO
import com.example.goodweather.domain.repository.WeatherRepository
import com.example.goodweather.network.ForecastApiService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: ForecastApiService,
) : WeatherRepository {

    override fun getForecast(
        latitude: Double,
        longitude: Double,
        precipitation: String,
        weatherCode: String,
        startDate: String,
        endDate: String
    ): Single<WeatherInfoDTO> {
        return api.getTodayForecast(
            latitude = latitude,
            longitude = longitude,
            precipitation = precipitation,
            weatherCode = weatherCode,
            startDate = startDate,
            endDate = endDate
        )
    }

    override fun getNextSixDaysForecast(
        latitude: Double,
        longitude: Double,
        precipitation: String,
        weatherCode: String,
    ): Single<List<WeatherInfoDTO>> {
        return api.getNextSixDaysForecast(
            latitude = latitude,
            longitude = longitude,
            precipitation = precipitation,
            weatherCode = weatherCode
        )
    }
}