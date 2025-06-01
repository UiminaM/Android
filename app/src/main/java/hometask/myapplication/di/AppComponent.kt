package hometask.myapplication.di

import hometask.myapplication.ui.weather.WeatherActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: WeatherActivity)
}
