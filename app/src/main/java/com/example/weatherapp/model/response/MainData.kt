package com.example.weatherapp.model.response

/**
 * Data class For MainData
 * @author hemeandra jain
 */
data class MainData(
    val feels_like: Double,
    val grnd_level: Int,
    val humidity: Int,
    val pressure: Int,
    val sea_level: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)