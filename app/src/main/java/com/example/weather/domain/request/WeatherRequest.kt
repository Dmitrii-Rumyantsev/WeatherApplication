package com.example.weather.domain.request;

import com.example.weather.data.WeatherCity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.gson.gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class WeatherApi {
    private val BASE_URL =
        "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
    private var LOCATION = "Moscow"
    private val GROUP = "unitGroup=metric"
    private val KEY = "key=ZH63FRCTTRJMN5X5285JXWY2G"
    private val CONTENT_TYPE = "contentType=json"
// https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/
// Moscow
// ?
// unitGroup=metric
// &
// key=ZH63FRCTTRJMN5X5285JXWY2G
// &
// contentType=json
    suspend fun doKtorRequest(cityName:String): WeatherCity {
        val client = HttpClient(Android) {
            install(ContentNegotiation) {
                gson()
            }
        }
        return withContext(Dispatchers.IO) {
            client.get("${BASE_URL}${cityName}?${GROUP}&${KEY}&${CONTENT_TYPE}")
                .body<WeatherCity>()
        }
    }
}