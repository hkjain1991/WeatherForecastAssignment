package com.example.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.response.WeatherDetails
import com.example.weatherapp.service.WeatherService
import kotlinx.coroutines.launch

class WeatherDetailsViewModel : ViewModel() {

    private val _weatherLiveData: MutableLiveData<List<WeatherDetails>?> =
        MutableLiveData<List<WeatherDetails>?>()

    val weatherLiveData: LiveData<List<WeatherDetails>?> = _weatherLiveData

    fun getWeatherDataAccordingToZipCode(
        zipcode: String,
        weatherService: WeatherService = WeatherService()
    ) {
        viewModelScope.launch {
            val weatherLists = weatherService.getUpcomingWeatherData(
                zipcode
            )
            weatherLists?.let {
                _weatherLiveData.value = it.list
            } ?: kotlin.run {
                _weatherLiveData.value = null
            }
        }
    }

}