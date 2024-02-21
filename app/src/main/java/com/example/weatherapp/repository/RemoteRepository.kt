package com.example.weatherapp.repository

import com.example.weatherapp.enum.TempUnits
import com.example.weatherapp.model.response.WeatherListData
import com.example.weatherapp.retrofit.APIClient
import com.example.weatherapp.retrofit.OpenWeatherApi
import com.example.weatherapp.utils.Constants

class RemoteRepository {
    suspend fun getUpcomingWeatherData(zipCode: String): WeatherListData? {
        val openWeatherApi = APIClient.getInstance().create(OpenWeatherApi::class.java)
        return openWeatherApi.getUpcomingDaysWeather(
            zipCode,
            Constants.TOTAL_NO_RESULT,
            Constants.APP_ID,
            TempUnits.Metric.name
        ).body()
    }
}