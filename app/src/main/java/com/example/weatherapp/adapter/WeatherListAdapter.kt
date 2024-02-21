package com.example.weatherapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.model.response.WeatherDetails
import com.example.weatherapp.model.response.WeatherListData
import com.example.weatherapp.utils.Constants
import com.example.weatherapp.utils.TimeUtil

class WeatherListAdapter(private var weatherList: List<WeatherDetails>) :
    RecyclerView.Adapter<WeatherListAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val humidity: TextView
        val date: TextView
        val imageView: ImageView

        init {
            humidity = view.findViewById(R.id.humidity)
            date = view.findViewById(R.id.date)
            imageView = view.findViewById(R.id.imgWeather)
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
        viewHolder.humidity.text = "${weatherDetails.main.temp}\u2103"
        viewHolder.date.text = TimeUtil.extractDateFromString(weatherDetails.dt_txt) + " | " +
                TimeUtil.extractTimeFromString(weatherDetails.dt_txt)
        Glide.with(viewHolder.itemView).load("https://openweathermap.org/img/wn/${weatherDetails.weather[0].icon}@2x.png").into(
            viewHolder.imageView
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(weatherLists: List<WeatherDetails>) {
        this.weatherList = weatherLists
        notifyDataSetChanged()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = weatherList.size
}