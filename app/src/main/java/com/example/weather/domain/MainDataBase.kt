package com.example.weather.domain

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weather.domain.dao.UserDao
import com.example.weather.domain.dao.WeatherDao
import com.example.weather.data.entity.User
import com.example.weather.data.entity.Weather

@Database(entities = [User::class, Weather::class], version = 1)
abstract class MainDataBase  : RoomDatabase(){
    abstract fun getUserDao(): UserDao

    abstract fun getWeatherDao(): WeatherDao

    companion object {
        @Volatile
        private var Instance: MainDataBase? = null

        fun getDataBase(context: Context): MainDataBase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MainDataBase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}