package com.example.weatherapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.weatherapp.model.Favorite
import java.util.concurrent.Flow


@Dao
interface WeatherDao {
    @Query(value = "SELECT * from fav_tbl")
    fun getFavorites():kotlinx.coroutines.flow.Flow<List<Favorite>>

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
}