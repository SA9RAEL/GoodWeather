package com.example.goodweather

import android.app.Application
import com.example.goodweather.di.AppComponent
import com.example.goodweather.di.DaggerAppComponent

class WeatherApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().withContext(this).build()
    }
}