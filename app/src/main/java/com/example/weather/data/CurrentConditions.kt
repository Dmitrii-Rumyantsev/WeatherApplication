package com.example.weather.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentConditions(
    @SerialName("datetime")
    val datetime: String,
    @SerialName("temp")
    val temp: Double,
    @SerialName("humidity")
    val humidity: Double,
    @SerialName("precip")
    val precip: Double,
    @SerialName("preciptype")
    val preciptype: String?,
    @SerialName("sunrise")
    val sunrise: String,
    @SerialName("sunset")
    val sunset: String
)