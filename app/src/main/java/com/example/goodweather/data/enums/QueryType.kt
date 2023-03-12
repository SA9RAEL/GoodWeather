package com.example.goodweather.data.enums

enum class QueryType(val title: String) {
    REQUIRED_PARAMS("temperature_2m,precipitation,weathercode,windspeed_10m"),
    CURRENT_WEATHER("true")
}
