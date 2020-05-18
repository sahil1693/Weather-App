package com.example.wheaterapp2

import android.content.Context
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.wheaterapp2.R
import com.example.wheaterapp2.R.string.api_key
import com.example.wheaterapp2.Weather
class WeatherNetworkClient(private val context: Context){

    private val apikey = "43ec8ce6fd6fb3dd84cbdb293e79e696"
    fun getWeatherForZipCode(zipcode : String): Call<Weather>{
        val network = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val weatherServices = network.create(WeatherService::class.java)
        return weatherServices.weatherByZip(zipcode,apikey);
    }
}