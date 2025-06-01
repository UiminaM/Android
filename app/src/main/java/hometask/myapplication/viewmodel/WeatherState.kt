package hometask.myapplication.viewmodel

import hometask.myapplication.data.model.WeatherResponse

sealed class WeatherState {
    object Loading : WeatherState()
    data class Success(val weather: WeatherResponse) : WeatherState()
    data class Error(val message: String) : WeatherState()
}