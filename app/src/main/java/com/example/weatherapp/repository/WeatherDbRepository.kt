package com.example.weatherapp.repository

import com.example.weatherapp.data.WeatherDao
import com.example.weatherapp.model.City
import com.example.weatherapp.model.Favorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class WeatherDbRepository @Inject constructor
    (private val weatherDao: WeatherDao) {
    fun getFavorites(): Flow<List<Favorite>> = weatherDao.getFavorites()
    suspend fun insertFavorites(favorite: Favorite) = weatherDao.insertFavorites(favorite)
suspend fun updateFavorites(favorite: Favorite)=weatherDao.updateFavorites(favorite)
suspend fun deleteAllFavorites(favorite: Favorite)=weatherDao.deleteAllFavorites()
suspend fun deleteFavorites(favorite: Favorite)=weatherDao.deleteFavorites(favorite)
    suspend fun getFavoritesById(city: String):Favorite =weatherDao.getFavById(city)

    }