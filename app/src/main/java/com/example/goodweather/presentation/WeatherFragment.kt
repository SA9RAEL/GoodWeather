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
import com.example.goodweather.R
import com.example.goodweather.WeatherApplication
import com.example.goodweather.data.const.ERROR
import com.example.goodweather.data.location.Location
import com.example.goodweather.databinding.FragmentWeatherBinding
import com.example.goodweather.presentation.viewmodel.view.ForecastView
import com.example.goodweather.presenter.WeatherPresenter
import com.example.goodweather.presenter.WeatherPresenterFactory
import com.tbruyelle.rxpermissions3.RxPermissions
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject


class WeatherFragment : MvpAppCompatFragment(), ForecastView {

    private val binding: FragmentWeatherBinding by viewBinding(CreateMethod.INFLATE)

    private var location: Location? = null

    @Inject
    lateinit var weatherPresenterFactory: WeatherPresenterFactory

    private val weatherPresenter: WeatherPresenter by lazy {
        weatherPresenterFactory.create()
    }

    private val currentDate = LocalDateTime.now()
    private val form = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())

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

        showTodayForecast()
        //showNextSixDaysForecast
    }

    override fun showTodayForecast() {
        binding.successContainer.visibility = View.VISIBLE

        weatherPresenter.showTodayForecast(
            location?.let { getCurrentLatitude(it) } ?: 55.7522,
            location?.let { getCurrentLongitude(it) } ?: 37.6173,
            form.format(currentDate),
            form.format(currentDate)
        )

    }

    override fun showNextSixDaysForecast() {
        binding.successContainer.visibility = View.VISIBLE

        weatherPresenter.showNextSixDaysForecast(
            location?.let { getCurrentLatitude(it) } ?: 55.7522,
            location?.let { getCurrentLongitude(it) } ?: 37.6173,
        )
    }

    @SuppressLint("CheckResult")
    fun getCurrentLatitude(location: Location): Double {
        RxPermissions(this)
            .request(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .subscribe { granted ->
                if (granted) {
                    location.getLastLatitude()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "No Location permissions",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        return location.getLastLatitude() ?: 55.7522
    }

    @SuppressLint("CheckResult")
    fun getCurrentLongitude(location: Location): Double {
        RxPermissions(this)
            .request(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .subscribe { granted ->
                if (granted) {
                    location.getLastLongitude()
                } else {
                    Toast.makeText(requireContext(), "No Location permissions", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        return location.getLastLongitude() ?: 37.6173
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

    override fun onDestroyView() {
        weatherPresenter.onDestroy()
        super.onDestroyView()
    }

}