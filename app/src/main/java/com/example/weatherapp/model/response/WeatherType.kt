package com.example.weatherapp.model.response

data class WeatherType(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)