package hometask.myapplication


data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind,
    val clouds: Clouds
)

data class Main(
    val temp: Double,
    val humidity: Int,
    val pressure: Int
)

data class Weather(
    val icon: String,
    val description: String
)

data class Wind(
    val speed: Double
)

data class Clouds(
    val all: Int
)

