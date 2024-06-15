package com.example.weather.domain.api

import android.util.Log
import com.example.weather.data.entity.TranslateRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.append
import io.ktor.util.InternalAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class YandexRequest {

    companion object {
        private val IAM_TOKEN = "AQVNz-kZT0x131cyJ_TgpC6xIC7luy3sZCv5I9nw"
        private val folderId = "b1gt6auvmapupvfm1c9i"
    }

    @OptIn(InternalAPI::class)
    suspend fun doTranslate(lang: String, texts: List<String>): String {
        return withContext(Dispatchers.IO) {
            val client = HttpClient()
            val requestBody = TranslateRequest(
                targetLanguageCode = lang,
                texts = texts,
                folderId = folderId
            )
            val response = client.post {
                url("https://translate.api.cloud.yandex.net/translate/v2/translate")
                headers {
                    append(HttpHeaders.ContentType, ContentType.Application.Json)
                    append(HttpHeaders.Authorization, "Api-Key $IAM_TOKEN")
                }
                setBody(Json.encodeToString(requestBody))
            }.body<String>()
            Log.d("Response", response)
            val translatedResponse = Json.decodeFromString<TranslateResponse>(response)
            Log.d("Info translate", translatedResponse.toString())
            var res = ""
            Log.d("YnadexRequest", translatedResponse.translations.toString())

            for (x  in translatedResponse.translations){
                res += "${x.text} "
                Log.d("YnadexRequest", x.text)
            }
            res.capitalize()
        }
    }
}

@Serializable
data class TranslateResponse(
    val translations: List<Translation>
)

@Serializable
data class Translation(
    val text: String,
    val detectedLanguageCode: String
)