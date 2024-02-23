package com.example.weatherapp.enum

/**
 * Temp Units which supports in the api to show the api e.g. Kelvin, C, F
 * @param name for sending to api
 * @author hemeandra jain
 */
enum class TempUnits(name: String) {
    Standard("Standard"),
    Metric("Metric"),
    Imperial("Imperial")
}