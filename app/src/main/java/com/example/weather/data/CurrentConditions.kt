package com.example.weather.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
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
):Parcelable