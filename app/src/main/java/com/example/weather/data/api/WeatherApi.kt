package com.example.weather.data.api

import com.example.weather.data.WeatherCity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.gson.gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherApi {
    private val BASE_URL =
        "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
    private val GROUP = "unitGroup=metric"
    private val KEY = "key=ZH63FRCTTRJMN5X5285JXWY2G"
    private val CONTENT_TYPE = "contentType=json"
    private var LOCATION = "Moscow"
    private var INCLUDE = "include=days"
    companion object{
        @Volatile
        private var instance : WeatherApi? = null

        fun getInstance() : WeatherApi{
            return  instance ?: synchronized(this){
                instance ?: WeatherApi().also { instance = it }
            }
        }
    }

    suspend fun doKtorRequest(): WeatherCity {
        val client = HttpClient {
            install(ContentNegotiation) {
                gson()
            }
        }
        return withContext(Dispatchers.IO) {
                client.get("${BASE_URL}${LOCATION}?${GROUP}&${INCLUDE}&${KEY}&${CONTENT_TYPE}").body<WeatherCity>()
        }
    }
}