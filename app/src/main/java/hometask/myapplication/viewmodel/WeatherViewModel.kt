package hometask.myapplication.viewmodel

import androidx.lifecycle.*
import hometask.myapplication.repository.WeatherRepository
import kotlinx.coroutines.launch
import java.io.IOException

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _state = MutableLiveData<WeatherState>()
    val state: LiveData<WeatherState> = _state

    fun loadWeather(city: String) {
        viewModelScope.launch {
            _state.value = WeatherState.Loading
            try {
                val weather = repository.getWeather(city)
                _state.value = WeatherState.Success(weather)
            } catch (e: IOException) {
                _state.value = WeatherState.Error("Нет подключения к интернету")
            } catch (e: Exception) {
                _state.value = WeatherState.Error("Ошибка: ${e.message}")
            }
        }
    }
}
