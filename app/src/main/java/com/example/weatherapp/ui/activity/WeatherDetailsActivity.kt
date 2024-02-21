package com.example.weatherapp.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.adapter.WeatherListAdapter
import com.example.weatherapp.utils.Constants
import com.example.weatherapp.utils.InternetConnection
import com.example.weatherapp.viewmodel.WeatherDetailsViewModel

class WeatherDetailsActivity : AppCompatActivity() {
    private val weatherDetailsViewModel: WeatherDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_details)
        setupClickListeners()
        setupObservers()
    }

    private fun setupObservers() {
        weatherDetailsViewModel.weatherLiveData.observe(this) { weatherList ->
            val progressBar = findViewById<ProgressBar>(R.id.proBar)
            val recyclerView: RecyclerView = findViewById(R.id.weatherList)
            val customAdapter = WeatherListAdapter(emptyList())
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = customAdapter
            progressBar.visibility = View.GONE
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
        val editText = findViewById<EditText>(R.id.zipCodeEdt)
        val progressBar = findViewById<ProgressBar>(R.id.proBar)
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