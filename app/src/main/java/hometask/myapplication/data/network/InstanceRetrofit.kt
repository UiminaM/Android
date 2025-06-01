package hometask.myapplication.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InstanceRetrofit {
    private const val BASE_URL = "https://api.openweathermap.org/"

    val instance: WeatherAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPI::class.java)
    }
}
