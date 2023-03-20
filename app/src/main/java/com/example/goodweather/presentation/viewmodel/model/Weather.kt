package com.example.goodweather.presentation.viewmodel.model

data class Weather(
    val temperature: Double,
    val time: String,
    val weatherCode: Int,
    val windDirection: Double,
    val windSpeed: Double
)