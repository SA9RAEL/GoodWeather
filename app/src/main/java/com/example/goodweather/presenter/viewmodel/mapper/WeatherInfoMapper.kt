package com.example.goodweather.presenter.viewmodel.mapper

import com.example.goodweather.data.model.CurrentWeatherDTO
import com.example.goodweather.presenter.viewmodel.model.Weather
import javax.inject.Inject

class WeatherInfoMapper @Inject constructor() : (CurrentWeatherDTO) -> Weather {
    override fun invoke(currentWeatherDTO: CurrentWeatherDTO): Weather {
        return with(currentWeatherDTO) {
            Weather(
                temperature = temperature,
                time = time,
                weatherCode = weatherCode,
                windDirection = windDirection,
                windSpeed = windSpeed
            )
        }
    }
}