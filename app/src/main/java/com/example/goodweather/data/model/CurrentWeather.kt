package com.example.goodweather.data.model

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    @SerializedName("temperature")
    val temperature: Double,
    @SerializedName("time")
    val time: String,
    @SerializedName("weather_code")
    val weatherCode: Int,
    @SerializedName("wind_direction")
    val windDirection: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double
)