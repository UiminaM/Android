package hometask.myapplication.data.api

import hometask.myapplication.data.api.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String = "1503f3bab210b6d39c7c0466829e2042",
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ru"
    ): retrofit2.Response<WeatherResponse>
}