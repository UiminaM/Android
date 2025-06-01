package hometask.myapplication.data.api

import hometask.myapplication.data.api.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String = "66fd309048bc08785a67e53eef222290",
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ru"
    ): retrofit2.Response<WeatherResponse>
}