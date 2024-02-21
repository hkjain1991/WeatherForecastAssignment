package com.example.weatherapp.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.adapter.WeatherListAdapter
import com.example.weatherapp.databinding.ActivityWeatherDetailsBinding
import com.example.weatherapp.utils.Constants
import com.example.weatherapp.utils.InternetConnection
import com.example.weatherapp.viewmodel.WeatherDetailsViewModel

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

    private fun setupObservers() {
        weatherDetailsViewModel.weatherLiveData.observe(this) { weatherList ->
            val recyclerView: RecyclerView = activityWeatherDetailsBinding.weatherList
            val customAdapter = WeatherListAdapter(emptyList())
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = customAdapter
            activityWeatherDetailsBinding.proBar.visibility = View.GONE
            weatherList?.let {
                recyclerView.visibility = View.VISIBLE
                customAdapter.updateList(it)
            } ?: kotlin.run {
                customAdapter.updateList(emptyList())
                recyclerView.visibility = View.GONE
                Toast.makeText(this, R.string.weather_info_unavailable, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setupClickListeners() {
        val editText = activityWeatherDetailsBinding.zipCodeEdt
        val progressBar = activityWeatherDetailsBinding.proBar
        editText.setOnClickListener {
            if (editText.length() < applicationContext.resources.getInteger(R.integer.zipcode_length)) {
                Toast.makeText(
                    this, R.string.zipcode_length_error, Toast.LENGTH_SHORT
                ).show()
            } else {
                if (InternetConnection.isNetworkAvailable(this)) {
                    progressBar.visibility = View.VISIBLE
                    weatherDetailsViewModel.getWeatherDataAccordingToZipCode(
                        "${editText.text}${Constants.COMMA}${Constants.COUNTRY_CODE}"
                    )
                } else {
                    Toast.makeText(this, R.string.internet_connection_error, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}