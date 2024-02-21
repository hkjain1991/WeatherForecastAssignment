package com.example.weatherapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.model.response.WeatherDetails
import com.example.weatherapp.utils.Constants
import com.example.weatherapp.utils.TimeUtil

class WeatherListAdapter(private val weatherList: List<WeatherDetails>) :
    RecyclerView.Adapter<WeatherListAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val humidity: TextView
        val weatherStatus: TextView
        val date: TextView
        val time: TextView

        init {
            humidity = view.findViewById(R.id.humidity)
            weatherStatus = view.findViewById(R.id.weatherStatus)
            date = view.findViewById(R.id.date)
            time = view.findViewById(R.id.time)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.weather_item_view, viewGroup, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n", "CheckResult")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val weatherDetails = weatherList[position]
        viewHolder.weatherStatus.text = weatherDetails.weather[0].description
        viewHolder.humidity.text = "${Constants.HUMIDITY}${weatherDetails.main.humidity}${Constants.PERCENTAGE}"
        viewHolder.time.text = TimeUtil.extractTimeFromString(weatherDetails.dt_txt)
        viewHolder.date.text = TimeUtil.extractDateFromString(weatherDetails.dt_txt)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = weatherList.size
}