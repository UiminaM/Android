package hometask.myapplication.repository

import hometask.myapplication.data.api.response.WeatherResponse
import hometask.myapplication.data.api.WeatherAPI

class WeatherRepository(private val api: WeatherAPI) {
    suspend fun getWeather(city: String): WeatherResponse {
        return api.getWeather(city).body()!!
    }
}
