package com.example.goodweather.data.repository

import com.example.goodweather.data.date.CurrentDate
import com.example.goodweather.data.location.LocationDataSource
import com.example.goodweather.data.model.WeatherInfoDTO
import com.example.goodweather.data.network.ForecastApiDataSource
import com.example.goodweather.domain.repository.WeatherRepository
import com.example.goodweather.presentation.viewmodel.mapper.WeatherInfoMapper
import com.example.goodweather.presentation.viewmodel.model.Weather
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val forecastApiDataSource: ForecastApiDataSource,
    private val locationDataSourceImpl: LocationDataSource,
    private val mapper: WeatherInfoMapper
) : WeatherRepository {

    override fun getForecast(
        isGranted: Boolean
    ): Single<Weather> {
        return locationDataSourceImpl
            .getLocation(isGranted)
            .flatMap { location ->
                forecastApiDataSource.todayForecast(
                    latitude = location.latitude,
                    longitude = location.longitude,
                    startDate = CurrentDate.form.format(CurrentDate.currentDate),
                    endDate = CurrentDate.form.format(CurrentDate.currentDate)
                )
            }
            .map { dto ->
                mapper(dto.currentWeatherDTO)
            }
    }

    override fun getNextSixDaysForecast(
    ): Single<List<WeatherInfoDTO>> {
        TODO()
    }

}