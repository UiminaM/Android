package hometask.myapplication.repository

import hometask.myapplication.data.model.WeatherResponse
import hometask.myapplication.data.network.WeatherAPI

class WeatherRepository(private val api: WeatherAPI) {
    suspend fun getWeather(city: String): WeatherResponse {
        return api.getWeather(city).body()!!
    }
}
