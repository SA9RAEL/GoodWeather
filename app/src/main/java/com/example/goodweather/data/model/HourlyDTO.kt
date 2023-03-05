package com.example.goodweather.data.model

import com.google.gson.annotations.SerializedName

data class HourlyDTO(
    @SerializedName("precipitation")
    val precipitation: List<Double>,
    @SerializedName("temperature_2m")
    val temperature_2m: List<Double>,
    @SerializedName("time")
    val time: List<String>,
    @SerializedName("windspeed_10m")
    val windSpeed_10m: List<Double>,
    @SerializedName("weathercode")
    val weathercode: List<Int>
)