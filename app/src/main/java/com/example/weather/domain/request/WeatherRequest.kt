package com.example.weather.domain.request

import android.util.Log
import com.example.weather.data.WeatherCity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json;
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

class WeatherRequest {
    companion object{
        private val BASE_URL =
            "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
        private var LOCATION = "Moscow/"
        private val GROUP = "today?unitGroup=metric"
        private var INCLUDE = "include=current"
        private val KEY = "key=ZH63FRCTTRJMN5X5285JXWY2G"
        private val CONTENT_TYPE = "contentType=json"
    //https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/
    // Moscow/
    // today?unitGroup=metric
    // &
    // include=current
    // &
    // key=ZH63FRCTTRJMN5X5285JXWY2G
    // &
    // contentType=json
    }
    suspend fun doKtorRequest(): WeatherCity {
        val client = HttpClient()
        val response = client.get("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/Sochi/today?unitGroup=metric&include=current&key=ZH63FRCTTRJMN5X5285JXWY2G&contentType=json").body<String>()
        Log.d("Info json", response)
        val weatherCity = Json {ignoreUnknownKeys = true}.decodeFromString<WeatherCity>(response)
        return weatherCity
    }
}