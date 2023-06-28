package com.example.goodweather.data.enums

enum class QueryType(val title: String) {
    HOURLY("temperature_2m"),
    CURRENT_WEATHER("true"),
    FORECAST_SEVEN_DAYS("7"),
    FORECAST_ONE_DAY("1"),
    TIMEZONE("auto")

}
