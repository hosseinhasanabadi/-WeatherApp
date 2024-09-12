package com.example.weatherapp.network

import com.example.weatherapp.model.Weather
import com.example.weatherapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    @GET(value = "data/2.5/forecast")
    suspend fun getWeather(
        @Query ("q") query:String,
        @Query ("units") units:String= "imperial",
       // @Query("cnt") cnt: Int = 7, // تعداد روزها را از طریق پارامتر `cnt` مشخص کن
        @Query ("appid") appid:String =Constants.API_KEY
    ):Weather
}