package com.example.weatherapp.repository

import android.util.Log
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.Weather
import com.example.weatherapp.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api:WeatherApi
) {
    suspend fun getWeather(cityQuery:String,
                        //   daysCount: Int = 7
    ):DataOrException<Weather,Boolean,Exception>  {
        val response = try {
            api.getWeather(query = cityQuery,
                //cnt = daysCount

            )


        }catch (e:Exception){
            Log.d("RTX","getWeather:$e")

            return  DataOrException(e=e)

        }
        Log.d("Inside","getWeather:$response")
        return DataOrException(data = response)


    }
}