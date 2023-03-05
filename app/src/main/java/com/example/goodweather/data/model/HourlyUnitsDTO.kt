package com.example.goodweather.data.model

import com.google.gson.annotations.SerializedName

data class HourlyUnitsDTO(
    @SerializedName("precipitation")
    val precipitation: String,
    @SerializedName("temperature_2m")
    val temperature2m: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("windspeed_10m")
    val windSpeed_10m: String,
    @SerializedName("weathercode")
    val weathercode: String
)