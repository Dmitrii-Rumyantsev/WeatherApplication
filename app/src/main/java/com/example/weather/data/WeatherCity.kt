package com.example.weather.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherCity(
    @SerialName("address")
    val address: String,
    @SerialName("currentConditions")
    val currentConditions: CurrentConditions,
    val days: List<Day>
)