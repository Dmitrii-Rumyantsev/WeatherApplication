package com.example.weather.data

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.ArrayList

@Serializable
@Parcelize
data class Day(
    @SerialName("datetime")
    val datetime: String,
    @SerialName("tempmax")
    val tempmax: Double,
    @SerialName("tempmin")
    val tempmin: Double,
    @SerialName("temp")
    val temp: Double,
    @SerialName("humidity")
    val humidity: Double,
    @SerialName("icon")
    val icon: String,
    @SerialName("sunrise")
    val sunrise: String,
    @SerialName("sunset")
    val sunset: String,
    @SerialName("precip")
    val precip: Double,
    @SerialName("windgust")
    val windgust: String,
    @SerialName("visibility")
    val visibility: String,
    @SerialName("uvindex")
    val uvindex: String,
    @SerialName("pressure")
    val pressure: String,
    val hours: List<Hour>
):Parcelable {
}