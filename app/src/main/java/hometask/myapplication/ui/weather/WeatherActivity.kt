package hometask.myapplication.ui.weather

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import hometask.myapplication.data.api.response.WeatherResponse
import hometask.myapplication.data.api.InstanceRetrofit
import hometask.myapplication.databinding.ActivityWeatherBinding
import hometask.myapplication.repository.WeatherRepository
import hometask.myapplication.utils.WeatherViewModelFactory
import hometask.myapplication.viewmodel.WeatherState
import hometask.myapplication.viewmodel.WeatherViewModel
import hometask.myapplication.App
import java.util.Locale
import javax.inject.Inject

class WeatherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherBinding

    @Inject
    lateinit var repository: WeatherRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val city = intent.getStringExtra("CITY") ?: error("Error with city")

        val viewModel: WeatherViewModel by viewModels {
            WeatherViewModelFactory(WeatherRepository(InstanceRetrofit.instance))
        }

        viewModel.state.observe(this) { state ->
            when (state) {
                is WeatherState.Loading -> binding.progressBar.visibility = View.VISIBLE
                is WeatherState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    updateUI(state.weather)
                }
                is WeatherState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }

        viewModel.loadWeather(city)
        binding.tvCityName.text = city
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI(weather: WeatherResponse) {
        binding.temperatureText.text = "Температура: ${weather.main.temp}°C"
        binding.descriptionText.text = weather.weather[0].description.capitalize(Locale.ROOT)
        binding.humidityText.text = "Влажность: ${weather.main.humidity}%"
        binding.windText.text = "Ветер: ${weather.wind.speed} м/с"
        binding.pressureText.text = "Давление: ${weather.main.pressure} гПа"
        binding.cloudsText.text = "Облачность: ${weather.clouds.all}%"


        Glide.with(this)
            .load("https://openweathermap.org/img/w/${weather.weather[0].icon}.png")
            .into(binding.weatherIcon)
    }
}

