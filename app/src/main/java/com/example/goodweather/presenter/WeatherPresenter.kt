package com.example.goodweather.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.goodweather.R
import com.example.goodweather.data.model.WeatherInfoDTO
import com.example.goodweather.domain.repository.WeatherRepository
import com.example.goodweather.presentation.viewmodel.model.Weather
import com.example.goodweather.presentation.viewmodel.view.ForecastView
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException

@InjectViewState(view = ForecastView::class)
class WeatherPresenter @AssistedInject constructor(
    private val weatherRepository: WeatherRepository
) : MvpPresenter<ForecastView>() {

    private val compositeDisposable = CompositeDisposable()

    fun showTodayForecast(isGranted: Boolean) {
         weatherRepository
            .getForecast(isGranted)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                ::todayForecastRequestSuccess,
                ::forecastRequestError
            ).addTo(compositeDisposable)
    }

//    fun showNextSixDaysForecast(
//        latitude: Double,
//        longitude: Double
//    ) {
//        weatherRepository
//            .getNextSixDaysForecast()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                ::nextSixDaysForecastRequestSuccess,
//                ::forecastRequestError
//            ).addTo(compositeDisposable)
//    }


    private fun todayForecastRequestSuccess(weather: Weather) {
        viewState.showTodayForecast()
    }

    private fun nextSixDaysForecastRequestSuccess(listWeatherInfoDTO: List<WeatherInfoDTO>) {
        viewState.showNextSixDaysForecast()
    }

    private fun forecastRequestError(error: Throwable) {
        if (error is HttpException) {
            val responseBody = error.response()?.errorBody()
            viewState.showError(message = responseBody?.string().orEmpty())
        } else {
            viewState.showError(message = R.string.no_internet_connection)
        }
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

}