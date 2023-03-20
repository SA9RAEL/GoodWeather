package com.example.goodweather.presentation.viewmodel.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.goodweather.presentation.viewmodel.model.Weather

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface ForecastView : MvpView {
//    fun showTodayForecast()
//    fun showNextSevenDaysForecast()
//    fun showError(message: String)
    fun bindInformation(weather: Weather)

}