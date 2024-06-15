package com.example.weather.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.weather.data.entity.Weather
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert
    fun insertWeather(weather: Weather)

    @Query("SELECT DISTINCT location FROM weather")
    suspend fun getAllLocations(): MutableList<String>

    @Query("DELETE FROM weather WHERE location = :location")
    suspend fun deleteWeatherByLocation(location: String)

    @Query("SELECT * FROM weather WHERE location = :location LIMIT 1")
    suspend fun getWeatherByLocation(location: String): Weather?
}