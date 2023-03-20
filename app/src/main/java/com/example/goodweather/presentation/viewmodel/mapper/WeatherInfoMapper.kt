package com.example.goodweather.presentation.viewmodel.mapper

import com.example.goodweather.R
import com.example.goodweather.data.const.*
import com.example.goodweather.data.model.CurrentWeatherDTO
import com.example.goodweather.presentation.viewmodel.model.Weather
import javax.inject.Inject

class WeatherInfoMapper @Inject constructor() : (CurrentWeatherDTO) -> Weather {
    override fun invoke(currentWeatherDTO: CurrentWeatherDTO): Weather {
        return with(currentWeatherDTO) {
            Weather(
                temperature = temperature,
                time = time,
                windDirection = windDirection,
                windSpeed = windSpeed,
                weatherCode = when (weatherCode) {
                    CLEAR_SKY -> R.drawable.ic_clear_day
                    MAINLY_CLEAR -> R.drawable.ic_clear_day
                    PARTLY_CLOUDY -> R.drawable.ic_partly_cloudy
                    OVERCAST -> R.drawable.ic_partly_cloudy
                    FOG -> R.drawable.ic_foggy
                    RIME_FOG -> R.drawable.ic_foggy
                    DRIZZLE_LIGHT -> R.drawable.ic_foggy
                    DRIZZLE_MODERATE -> R.drawable.ic_foggy
                    DRIZZLE_INTENSITY -> R.drawable.ic_foggy
                    FREEZING_DRIZZLE_LIGHT -> R.drawable.ic_foggy
                    FREEZING_DRIZZLE_INTENSITY -> R.drawable.ic_foggy
                    RAIN_SLIGHT -> R.drawable.ic_rainy
                    RAIN_MODERATE -> R.drawable.ic_rainy
                    RAIN_HEAVY_INTENSITY -> R.drawable.ic_rainy
                    FREEZING_LIGHT -> R.drawable.ic_cloudy_snowing
                    FREEZING_HEAVY_INTENSITY -> R.drawable.ic_cloudy_snowing
                    SNOW_FALL_SLIGHT -> R.drawable.ic_cloudy_snowing
                    SNOW_FALL_MODERATE -> R.drawable.ic_cloudy_snowing
                    SNOW_FALL_HEAVY_INTENSITY -> R.drawable.ic_cloudy_snowing
                    SNOW_GRAINS -> R.drawable.ic_cloudy_snowing
                    RAIN_SHOWERS_SLIGHT -> R.drawable.ic_rainy
                    RAIN_SHOWERS_MODERATE -> R.drawable.ic_rainy
                    RAIN_SHOWERS_VIOLENT -> R.drawable.ic_rainy
                    SNOW_SHOWERS_SLIGHT -> R.drawable.ic_cloudy_snowing
                    SNOW_SHOWERS_HEAVY -> R.drawable.ic_cloudy_snowing
                    else -> R.drawable.ic_clear_day
                }
            )
        }
    }
}