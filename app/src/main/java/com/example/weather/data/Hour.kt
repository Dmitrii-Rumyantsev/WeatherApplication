package com.example.weather.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Hour(
    @SerialName("datetime")
    val datetime: String,
    @SerialName("icon")
    val icon: String
): Parcelable {
}