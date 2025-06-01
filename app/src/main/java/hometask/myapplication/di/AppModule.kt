package hometask.myapplication.di

import dagger.Module
import dagger.Provides
import hometask.myapplication.data.api.InstanceRetrofit
import hometask.myapplication.data.api.WeatherAPI
import hometask.myapplication.repository.WeatherRepository
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherAPI = InstanceRetrofit.instance

    @Provides
    @Singleton
    fun provideWeatherRepository(api: WeatherAPI): WeatherRepository = WeatherRepository(api)
}
