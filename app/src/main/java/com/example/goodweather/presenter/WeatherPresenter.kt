package com.example.goodweather.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.goodweather.R
import com.example.goodweather.domain.repository.WeatherRepository
import com.example.goodweather.presentation.viewmodel.view.ForecastView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException

@InjectViewState
class WeatherPresenter(
    private val weatherRepository: WeatherRepository
) : MvpPresenter<ForecastView>() {

    fun showForecast(
        latitude: Double,
        longitude: Double,
        precipitation: String,
        weatherCode: String,
        startDate: String,
        endDate: String
    ) {

        weatherRepository
            .getForecast(
                latitude = latitude,
                longitude = longitude,
                precipitation = precipitation,
                weatherCode = weatherCode,
                startDate = startDate,
                endDate = endDate
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showTodayForecast()
            }, { error ->
                if (error is HttpException) {
                    val responseBody = error.response()?.errorBody()
                    viewState.showError(message = responseBody?.string().orEmpty())
                } else {
                    viewState.showError(message = R.string.no_internet_connection)
                }

            })
    }


}