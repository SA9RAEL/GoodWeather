package com.example.goodweather.data.location

import android.annotation.SuppressLint
import android.location.LocationManager
import javax.inject.Inject


class Location @Inject constructor(
    private val locationManager: LocationManager,
) {

    @SuppressLint("MissingPermission")
    fun getLastLatitude() = locationManager
        .getLastKnownLocation(LocationManager.GPS_PROVIDER)
        ?.latitude

    @SuppressLint("MissingPermission")
    fun getLastLongitude() =
        locationManager
            .getLastKnownLocation(LocationManager.GPS_PROVIDER)
            ?.longitude

}