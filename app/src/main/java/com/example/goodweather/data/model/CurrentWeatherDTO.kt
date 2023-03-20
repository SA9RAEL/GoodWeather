package com.example.goodweather.data.model

import com.google.gson.annotations.SerializedName

data class CurrentWeatherDTO(
    @SerializedName("temperature")
    val temperature: Double,
    @SerializedName("time")
    val time: String,
    @SerializedName("weathercode")
    val weatherCode: Int,
    @SerializedName("winddirection")
    val windDirection: Double,
    @SerializedName("windspeed")
    val windSpeed: Double
)