package com.example.goodweather.presentation.viewmodel.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface ForecastView : MvpView {
    fun showTodayForecast()
    fun showNextSixDaysForecast()
    fun showError(message: String)
    fun showError(message: Int)
}