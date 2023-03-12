package com.example.goodweather.data.repository


import com.example.goodweather.data.date.CurrentDate
import com.example.goodweather.data.location.LocationDataSourceImpl
import com.example.goodweather.data.model.WeatherInfoDTO
import com.example.goodweather.data.network.ForecastApiDataSource
import com.example.goodweather.domain.repository.WeatherRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val forecastApiDataSource: ForecastApiDataSource,
    private val locationDataSourceImpl: LocationDataSourceImpl
) : WeatherRepository {

    override fun getForecast(
    ): Single<WeatherInfoDTO> {
        return locationDataSourceImpl
            .getLocation()
            .flatMap { location ->
                forecastApiDataSource.todayForecast(
                    latitude = location.latitude,
                    longitude = location.longitude,
                    startDate = CurrentDate.form.format(CurrentDate.currentDate),
                    endDate = CurrentDate.form.format(CurrentDate.currentDate)
                )
            }
    }

    override fun getNextSixDaysForecast(
    ): Single<List<WeatherInfoDTO>> {
        TODO()
    }

}