package com.example.weatherapp.retrofit

import com.example.weatherapp.enum.TempUnits
import com.example.weatherapp.model.response.WeatherDetails
import com.example.weatherapp.model.response.WeatherListData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface to hold the all api for open weather forecast
 * @author hemeandra jain
 */
interface OpenWeatherApi {

    @GET("/data/2.5/forecast")
    suspend fun getUpcomingDaysWeather(
        @Query("zip") zipcode: String,
        @Query("cnt") totalResults: Int,
        @Query("APPID") appId: String,
        @Query("units") tempMeasure: String = TempUnits.Metric.name
    ): Response<WeatherListData>
}