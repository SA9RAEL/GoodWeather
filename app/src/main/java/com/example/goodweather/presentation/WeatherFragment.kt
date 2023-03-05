package com.example.goodweather.presentation


import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.goodweather.R
import com.example.goodweather.data.const.ERROR
import com.example.goodweather.data.const.PRECIPITATION
import com.example.goodweather.data.const.WEATHER_CODE
import com.example.goodweather.databinding.FragmentWeatherBinding
import com.example.goodweather.domain.repository.WeatherRepository
import com.example.goodweather.presentation.viewmodel.view.ForecastView
import com.example.goodweather.presenter.WeatherPresenter
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

const val TAG = "TAG"

class WeatherFragment : MvpAppCompatFragment(), ForecastView {

    private val binding: FragmentWeatherBinding by viewBinding(CreateMethod.INFLATE)
    init {
         showTodayForecast()
         // showNextSixDaysForecast()
    }

    @Inject
    lateinit var repository: WeatherRepository

    @InjectPresenter
    lateinit var weatherPresenter: WeatherPresenter

    private lateinit var locationManager: LocationManager

    @ProvidePresenter
    fun provideWeatherPresenter(): WeatherPresenter = WeatherPresenter(repository)

    private val currentDate =
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).toString()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun showTodayForecast() {
        binding.successContainer.visibility = View.VISIBLE

        weatherPresenter.showForecast(
            getLatitude() ?: 0.00,
            getLongitude() ?: 0.00,
            PRECIPITATION,
            WEATHER_CODE,
            currentDate,
            currentDate
        )

    }

    override fun showNextSixDaysForecast() {
        TODO("Not yet implemented")
    }

    override fun showError(message: String) =
        with(binding) {
            errorContainer.visibility = View.VISIBLE
            binding.errorTextView.text = ERROR
        }


    override fun showError(message: Int) =
        with(binding) {
            errorContainer.visibility = View.VISIBLE
            errorTextView.text = getString(R.string.no_internet_connection)
        }


    private fun getLatitude(): Double? = if (ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)?.latitude
    } else {
        throw Exception("Missing location permission")
    }

    private fun getLongitude(): Double? = if (ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)?.longitude
    } else {
        throw Exception("Missing location permission")
    }

}