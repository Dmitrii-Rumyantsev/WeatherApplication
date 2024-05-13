package com.example.weather.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Day(
    @SerialName("datetime")
    val dateTime: String,
    @SerialName("tempmax")
    val tempmax: Double,
    @SerialName("tempmin")
    val tempmin: Double,
    @SerialName("temp")
    val temp: Double,
    @SerialName("humidity")
    val humidity: Double,
    val preciptype: List<String?> = listOf("Default"),
    @SerialName("sunrise")
    val sunrise: String,
    @SerialName("sunset")
    val sunset: String
)