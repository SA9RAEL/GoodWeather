package com.example.goodweather.presenter

import dagger.assisted.AssistedFactory

@AssistedFactory
interface WeatherPresenterFactory {
    fun create(): WeatherPresenter
}