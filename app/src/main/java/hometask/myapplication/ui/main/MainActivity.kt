package hometask.myapplication.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import hometask.myapplication.viewmodel.ValidationResult
import hometask.myapplication.databinding.ActivityMainBinding
import hometask.myapplication.ui.weather.WeatherActivity
import hometask.myapplication.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {
            val city = binding.etCity.text.toString().trim()
            when (val result = viewModel.validateCityName(city)) {
                is ValidationResult.Success -> {
                    startActivity(Intent(this, WeatherActivity::class.java).apply {
                        putExtra("CITY", city)
                    })
                }
                is ValidationResult.Error -> {
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
