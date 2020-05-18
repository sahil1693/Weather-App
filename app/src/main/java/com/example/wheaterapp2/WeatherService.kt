package com.example.wheaterapp2

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService{
    @GET("/data/2.5/weather")
    fun weatherByZip(
        @Query("zip") zip: String,
        @Query("appid") apikey: String


    ): retrofit2.Call<Weather>

}