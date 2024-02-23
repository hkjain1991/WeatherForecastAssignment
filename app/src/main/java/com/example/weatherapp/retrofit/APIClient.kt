package com.example.weatherapp.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Class to get [Retrofit] instance
 * @author hemeandra jain
 */
object APIClient {

    private const val BASE_URL = "https://api.openweathermap.org/"

    /**
     * Gets the instance for Retrofit
     */
    fun getInstance(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }

}