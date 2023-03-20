package com.example.goodweather.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import com.example.goodweather.data.const.DEFAULT_LATITUDE
import com.example.goodweather.data.const.DEFAULT_LONGITUDE
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@SuppressLint("MissingPermission")
class LocationDataSourceImpl @Inject constructor(
    private val context: Context
) : LocationDataSource {

    override fun getLocation(isGranted: Boolean): Single<MyLocation> {
        (context.getSystemService(Context.LOCATION_SERVICE) as LocationManager).apply {
            if (isProviderEnabled(LocationManager.GPS_PROVIDER) && isGranted) {
                getLastKnownLocation(LocationManager.GPS_PROVIDER)?.let { location ->
                    return Single.just(
                        MyLocation(
                            latitude = location.latitude, longitude = location.longitude
                        )
                    )
                }
            }
        }
        return Single.just(MyLocation(latitude = DEFAULT_LATITUDE, longitude = DEFAULT_LONGITUDE))
    }

}