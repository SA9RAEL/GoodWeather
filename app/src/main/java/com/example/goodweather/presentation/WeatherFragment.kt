package com.example.goodweather.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.goodweather.WeatherApplication
import com.example.goodweather.databinding.FragmentWeatherBinding
import com.example.goodweather.presentation.viewmodel.model.Weather
import com.example.goodweather.presentation.viewmodel.view.ForecastView
import com.example.goodweather.presenter.WeatherPresenter
import com.example.goodweather.presenter.WeatherPresenterFactory
import com.tbruyelle.rxpermissions3.RxPermissions
import javax.inject.Inject

class WeatherFragment : MvpAppCompatFragment(), ForecastView {

    private val binding: FragmentWeatherBinding by viewBinding(CreateMethod.INFLATE)

    @Inject
    lateinit var weatherPresenterFactory: WeatherPresenterFactory

    private val weatherPresenter: WeatherPresenter by lazy {
        weatherPresenterFactory.create()
    }

    override fun onAttach(context: Context) {
        (context.applicationContext as WeatherApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        todayForecast()
        weatherPresenter.showTodayForecast(true)
       // showTodayForecast()
        //showNextSixDaysForecast
    }

//    override fun showTodayForecast() {
//        binding.successContainer.visibility = View.VISIBLE
//        todayForecast()
//    }
//
//    override fun showNextSevenDaysForecast() {
//        TODO()
//
//    }

    @SuppressLint("CheckResult")
    private fun todayForecast() {
        RxPermissions(this)
            .request(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .subscribe(
                ::permissionGranted,
                ::permissionsNotGranted
            )
    }

//    @SuppressLint("CheckResult")
//    fun sixDaysForecast() {
//        RxPermissions(this)
//            .request(
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            )
//            .subscribe(
//                ::
//            )
//
//    }


    private fun permissionGranted(granted: Boolean) {
        weatherPresenter.showTodayForecast(granted)
    }

    private fun permissionsNotGranted(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
    }

    override fun bindInformation(weather: Weather) {
        binding.successContainer.visibility = View.VISIBLE
        with(binding) {
            weatherIcon.setImageResource(weather.weatherCode)
            temperatureTextView.text = weather.temperature.toString()
            windDirectionResultTextView.text = weather.windDirection.toString()
            windSpeedResult.text = weather.windSpeed.toString()
            dateTextView.text = weather.time.dropLast(6).replace("-", "/")
        }
    }

//    override fun showError(message: String) =
//        with(binding) {
//            errorContainer.visibility = View.VISIBLE
//            binding.errorTextView.text = ERROR
//        }

    override fun onDestroyView() {
        weatherPresenter.onDestroy()
        super.onDestroyView()
    }

}