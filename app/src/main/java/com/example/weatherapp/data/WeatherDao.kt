package com.example.weatherapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.weatherapp.model.Favorite
import kotlinx.coroutines.flow.Flow


@Dao
interface WeatherDao {
    @Query(value = "SELECT * from fav_tbl")
    fun getFavorites():Flow<List<Favorite>>

    @Query(value = "SELECT * from fav_tbl where city =:city")
    suspend fun getFavById (city:String):Favorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorites(favorite: Favorite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorites(favorite: Favorite)
@Query("DELETE from fav_tbl")
    suspend fun  deleteAllFavorites()
@Delete
    suspend fun deleteFavorites(favorite: Favorite)



    // Unit table
    @Query("SELECT * from settings_tbl")
    fun getUnits(): Flow<List<Unit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unit: com.example.weatherapp.model.Unit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(unit: com.example.weatherapp.model.Unit)

    @Query("DELETE from settings_tbl")
    suspend fun deleteAllUnits()

    @Delete
    suspend fun deleteUnit(unit: com.example.weatherapp.model.Unit)
}