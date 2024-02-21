package com.example.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.Locale

class TimeUtil {
    companion object {
        fun extractTimeFromString(dateTimeString: String): String {
            return try {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                val date = inputFormat.parse(dateTimeString)
                outputFormat.format(date!!)
            } catch (e: Exception) {
                ""
            }
        }

        fun extractDateFromString(dateTimeString: String): String {
            return try {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                val date = inputFormat.parse(dateTimeString)
                outputFormat.format(date!!)
            } catch (e: Exception) {
                ""
            }
        }
    }
}