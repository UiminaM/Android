package hometask.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hometask.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {
            val city = binding.etCity.text.toString().trim()
            if (city.any { it.isDigit() }) {
                Toast.makeText(this, "Название города не должно содержать цифры", Toast.LENGTH_SHORT).show()
            } else if (city.length !in 2..60) {
                Toast.makeText(this, "Название города должно быть от 2 до 60 символов", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, WeatherActivity::class.java).apply {
                    putExtra("CITY", city)
                }
                startActivity(intent)
            }
        }
    }
}