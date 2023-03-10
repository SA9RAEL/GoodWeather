package com.example.goodweather.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import com.example.goodweather.data.const.DEFAULT_LATITUDE
import com.example.goodweather.data.const.DEFAULT_LONGITUDE
import com.google.android.gms.location.FusedLocationProviderClient
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@SuppressLint("MissingPermission")
class LocationDataSourceImpl @Inject constructor(
    private val context: Context,
    private var fusedLocationProviderClient: FusedLocationProviderClient
) : LocationDataSource {

    override fun getLocation(): Single<MyLocation> {
        (context.getSystemService(Context.LOCATION_SERVICE) as LocationManager).apply {
            getLastKnownLocation(LocationManager.GPS_PROVIDER)?.let { location ->
                return Single.just(
                    MyLocation(
                        latitude = location.latitude, longitude = location.longitude
                    )
                )
            }
        }
        return Single.just(MyLocation(latitude = DEFAULT_LATITUDE, longitude = DEFAULT_LONGITUDE))
    }

}