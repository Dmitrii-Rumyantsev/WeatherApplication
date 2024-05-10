package com.example.weather.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherCity(
    @SerialName("resolvedAddress")
    val city: String,
    val curentTime: String,
    val datetime: String,
    val sunrise: String,
    val sunset: String,
    val preciptype: String,
    val precip: Double,
    val tempmax: Double,
    val tempmin: Double,
    val temp: Double,
    val humidity: Double,
    val uvindex: Int,
    val pressure: Int
)