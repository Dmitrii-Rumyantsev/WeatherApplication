package com.example.weather.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "weather",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("userId"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["location"], unique = true)]
)
data class Weather(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val location: String,
)
