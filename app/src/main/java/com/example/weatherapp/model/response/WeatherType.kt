package com.example.weatherapp.model.response

/**
 * Data class For Weather Type e.g. Cloudy
 * @author hemeandra jain
 */
data class WeatherType(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)