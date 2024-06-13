package com.example.weather.data.dao

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

    @Query("SELECT * FROM weather")
    suspend fun getWeather(): Flow<List<Weather>>
}