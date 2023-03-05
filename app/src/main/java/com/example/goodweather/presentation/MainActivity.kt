package com.example.goodweather.presentation

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.goodweather.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportActionBar != null) supportActionBar?.hide()

        if (savedInstanceState == null) {
            val fragment = WeatherFragment()

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container_view_tag, fragment)
                .commit()
        }

        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            0
        )


    }
}