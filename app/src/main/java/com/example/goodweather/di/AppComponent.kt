package com.example.goodweather.di

import android.content.Context
import com.example.goodweather.presentation.WeatherFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AllModules::class])
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {

        fun withContext(@BindsInstance context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(fragment: WeatherFragment)
    
}
