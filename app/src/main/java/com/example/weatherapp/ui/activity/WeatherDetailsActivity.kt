package com.example.weatherapp.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.adapter.WeatherListAdapter
import com.example.weatherapp.databinding.ActivityWeatherDetailsBinding
import com.example.weatherapp.enum.WeatherDetailsScreenError
import com.example.weatherapp.viewmodel.WeatherDetailsViewModel

/**
 * Activity for showing weather forecast
 */
class WeatherDetailsActivity : AppCompatActivity() {
    private val weatherDetailsViewModel: WeatherDetailsViewModel by viewModels()
    private lateinit var activityWeatherDetailsBinding: ActivityWeatherDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        activityWeatherDetailsBinding = ActivityWeatherDetailsBinding.inflate(layoutInflater)
        setContentView(activityWeatherDetailsBinding.root)
        setupClickListeners()
        setupObservers()
    }

    /**
     * Sets up the observers
     */
    private fun setupObservers() {
        val recyclerView: RecyclerView = activityWeatherDetailsBinding.weatherList
        val customAdapter = WeatherListAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter
        weatherDetailsViewModel.weatherLiveData.observe(this) { weatherList ->
            activityWeatherDetailsBinding.proBar.visibility = View.GONE
            // We must have data in weather list as We have checked all error condition in view model
            weatherList?.let {
                recyclerView.visibility = View.VISIBLE
                customAdapter.updateList(it)
            } ?: Log.d("App Error", "Weather list should not be empty")
        }
        weatherDetailsViewModel.error.observe(this) {
            activityWeatherDetailsBinding.weatherList.visibility = View.GONE
            activityWeatherDetailsBinding.proBar.visibility = View.GONE
            when (it) {
                WeatherDetailsScreenError.MAX_LENGTH -> Toast.makeText(
                    this,
                    R.string.zipcode_length_error,
                    Toast.LENGTH_SHORT
                ).show()

                WeatherDetailsScreenError.INTERNET_NOT_AVAILABLE -> Toast.makeText(
                    this,
                    R.string.internet_connection_error,
                    Toast.LENGTH_SHORT
                ).show()

                WeatherDetailsScreenError.RESULTS_NOT_AVAILABLE -> Toast.makeText(
                    this,
                    R.string.weather_info_unavailable,
                    Toast.LENGTH_SHORT
                ).show()

                else -> {
                    activityWeatherDetailsBinding.weatherList.visibility = View.VISIBLE
                }
            }
        }
    }

    /**
     * Sets up the click listeners
     */
    private fun setupClickListeners() {
        val editText = activityWeatherDetailsBinding.zipCodeEdt
        val progressBar = activityWeatherDetailsBinding.proBar
        editText.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            weatherDetailsViewModel.getWeatherDataAccordingToZipCode(
                "${editText.text}",
                this
            )
        }
    }
}