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
import com.example.goodweather.databinding.FragmentWeatherBinding
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

        weatherPresenter.showTodayForecast()
        showTodayForecast()
        //showNextSixDaysForecast
    }

    override fun showTodayForecast() {
        binding.successContainer.visibility = View.VISIBLE
        todayForecast()
    }

    override fun showNextSixDaysForecast() {
        binding.successContainer.visibility = View.VISIBLE


    }

    @SuppressLint("CheckResult")
    private fun todayForecast() {
        RxPermissions(this)
            .request(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .subscribe(
                ::isPermissionGranted,
                ::isPermissionsNotGranted
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


    private fun isPermissionGranted(granted: Boolean) {
        weatherPresenter.showTodayForecast()
    }

    private fun isPermissionsNotGranted(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT)
            .show()
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