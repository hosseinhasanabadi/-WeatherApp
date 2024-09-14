package com.example.weatherapp.repository

import com.example.weatherapp.data.WeatherDao
import com.example.weatherapp.model.Favorite
import kotlinx.coroutines.flow.Flow
import com.example.weatherapp.model.Unit

import javax.inject.Inject


class WeatherDbRepository @Inject constructor
    (private val weatherDao: WeatherDao) {
    fun getFavorites(): Flow<List<Favorite>> = weatherDao.getFavorites()
    suspend fun insertFavorites(favorite: Favorite) = weatherDao.insertFavorites(favorite)
suspend fun updateFavorites(favorite: Favorite)=weatherDao.updateFavorites(favorite)
suspend fun deleteAllFavorites(favorite: Favorite)=weatherDao.deleteAllFavorites()
suspend fun deleteFavorites(favorite: Favorite)=weatherDao.deleteFavorites(favorite)
    suspend fun getFavoritesById(city: String):Favorite =weatherDao.getFavById(city)



    fun getUnits(): Flow<List<kotlin.Unit>> = weatherDao.getUnits()
    suspend fun insertUnit(unit: Unit) = weatherDao.insertUnit(unit)
    suspend fun updateUnit(unit: Unit) = weatherDao.updateUnit(unit)
    suspend fun deleteAllUnits() = weatherDao.deleteAllUnits()
    suspend fun deleteUnit(unit:Unit) = weatherDao.deleteUnit(unit)
    }