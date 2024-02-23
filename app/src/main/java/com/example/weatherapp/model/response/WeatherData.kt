package com.example.weatherapp.model.response

/**
 * Data class For WeatherData
 * @author hemeandra jain
 */
data class WeatherData(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val dt: Int,
    val id: Int,
    val main: MainData,
    val name: String,
    val sys: SunData,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherType>,
    val wind: Wind
)