package com.example.weather.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class WeatherCity(
    @SerialName("address")
    val address: String,
    @SerialName("currentConditions")
    val currentConditions: CurrentConditions,
    val days: List<Day>
):Parcelable {}