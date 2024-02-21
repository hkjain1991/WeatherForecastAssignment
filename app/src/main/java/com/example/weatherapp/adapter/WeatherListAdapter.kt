package com.example.weatherapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.WeatherItemViewBinding
import com.example.weatherapp.model.response.WeatherDetails
import com.example.weatherapp.utils.TimeUtil

class WeatherListAdapter(private var weatherList: List<WeatherDetails>) :
    RecyclerView.Adapter<WeatherListAdapter.ViewHolder>() {

    private lateinit var weatherItemViewBinding: WeatherItemViewBinding

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(binding: WeatherItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val temp: TextView
        val date: TextView
        val imageView: ImageView

        init {
            temp = binding.temp
            date = binding.date
            imageView = binding.imgWeather
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        weatherItemViewBinding = WeatherItemViewBinding.inflate(
            LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(weatherItemViewBinding)
    }

    @SuppressLint("SetTextI18n", "CheckResult")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val weatherDetails = weatherList[position]
        viewHolder.temp.text = "${weatherDetails.main.temp}\u2103"
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