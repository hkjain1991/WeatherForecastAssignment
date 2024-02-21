package com.example.weatherapp.model.response

data class WeatherDetails(
    val main: MainData,
    val weather: List<WeatherType>,
    val clouds: Clouds,
    val wind: Wind,
    val dt_txt: String
)
