package com.example.weatherapp.viewmodel

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.R
import com.example.weatherapp.enum.WeatherDetailsScreenError
import com.example.weatherapp.model.response.WeatherDetails
import com.example.weatherapp.repository.RemoteRepository
import com.example.weatherapp.utils.Constants
import com.example.weatherapp.utils.InternetConnection
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * View Model for [WeatherDetailsActivity] and [RemoteRepository]
 * @author hemeandra jain
 */
class WeatherDetailsViewModel : ViewModel() {

    private val _weatherLiveData: MutableLiveData<List<WeatherDetails>?> =
        MutableLiveData<List<WeatherDetails>?>()

    val weatherLiveData: LiveData<List<WeatherDetails>?> = _weatherLiveData

    private val _error: MutableLiveData<WeatherDetailsScreenError?> =
        MutableLiveData<WeatherDetailsScreenError?>()

    val error: LiveData<WeatherDetailsScreenError?> = _error

    fun getWeatherDataAccordingToZipCode(
        zipcode: String,
        context: Context,
        remoteRepository: RemoteRepository = RemoteRepository()
    ) {
        if (zipcode.length < context.resources.getInteger(R.integer.zipcode_length)) {
            _error.value = WeatherDetailsScreenError.MAX_LENGTH
        } else {
            if (!InternetConnection.isNetworkAvailable(context)) {
                _error.value = WeatherDetailsScreenError.INTERNET_NOT_AVAILABLE
            } else {
                callUpcomingWeatherDetails(
                    "${zipcode}${Constants.COMMA}${Constants.COUNTRY_CODE}",
                    remoteRepository
                )
            }
        }
    }

    @VisibleForTesting
    fun callUpcomingWeatherDetails(
        zipcode: String,
        remoteRepository: RemoteRepository,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        viewModelScope.launch(dispatcher) {
            val weatherLists = remoteRepository.getUpcomingWeatherData(
                zipcode
            )
            weatherLists?.let {
                _weatherLiveData.postValue(it.list)
                _error.postValue(null)
            } ?: kotlin.run {
                _weatherLiveData.postValue(null)
                _error.postValue(WeatherDetailsScreenError.RESULTS_NOT_AVAILABLE)
            }
        }
    }
}