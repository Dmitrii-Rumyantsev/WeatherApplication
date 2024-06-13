package com.example.weather.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 1,
    @ColumnInfo(name = "location_now")
    val location_now: String,
    @ColumnInfo(name = "theme")
    val theme: Boolean,
)