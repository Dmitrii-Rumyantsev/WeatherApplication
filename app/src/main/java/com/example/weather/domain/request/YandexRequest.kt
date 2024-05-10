package com.example.weather.domain.request

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.append
import io.ktor.util.InternalAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class YandexRequest {

    companion object {
        private val IAM_TOKEN = "AQVNz-kZT0x131cyJ_TgpC6xIC7luy3sZCv5I9nw"
        private val folderId = "b1gt6auvmapupvfm1c9i"
    }

    @OptIn(InternalAPI::class)
    suspend fun doTranslate(lang: String, texts:List<String>) {
        return withContext(Dispatchers.IO){
            val client = HttpClient()
            val requestBody = mapOf(
                "targetLanguageCode" to lang,
                "texts" to texts,
                "folderId" to folderId
            )
            val response = client.post {
                url("https://translate.api.cloud.yandex.net/translate/v2/translate")
                headers {
                    append(HttpHeaders.ContentType, ContentType.Application.Json)
                    append(HttpHeaders.Authorization, "Api-Key $IAM_TOKEN")
                }
                body = Json.encodeToString(requestBody)
            }
            val str = response.body<String>()
            Log.d("Info translate", str)
        }

    }
}