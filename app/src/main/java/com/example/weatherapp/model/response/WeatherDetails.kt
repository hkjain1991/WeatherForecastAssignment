package com.example.weatherapp.model.response

/**
 * Data class For Weather Details
 * @author hemeandra jain
 */
data class WeatherDetails(
    val main: MainData,
    val weather: List<WeatherType>,
    val clouds: Clouds,
    val wind: Wind,
    val dt_txt: String
)
