package com.example.goodweather.data.location

import io.reactivex.rxjava3.core.Single

interface LocationDataSource {
    fun getLocation(isGranted: Boolean): Single<MyLocation>
}