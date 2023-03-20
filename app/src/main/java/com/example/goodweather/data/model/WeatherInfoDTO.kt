package com.example.goodweather.data.model

import com.google.gson.annotations.SerializedName

data class WeatherInfoDTO(
    @SerializedName("current_weather")
    val currentWeatherDTO: CurrentWeatherDTO,
    @SerializedName("elevation")
    val elevation: Double,
    @SerializedName("daily")
    val daily: DailyDTO,
    @SerializedName("daily_units")
    val dailyUnits: DailyUnitsDTO,
    @SerializedName("generationtime_ms")
    val generationTimeMs: Double,
    @SerializedName("hourly")
    val hourlyDTO: HourlyDTO,
    @SerializedName("hourly_units")
    val hourlyUnitsDTO: HourlyUnitsDTO,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Int
)