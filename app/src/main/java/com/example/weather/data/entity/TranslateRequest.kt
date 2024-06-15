package com.example.weather.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class TranslateRequest(
    val targetLanguageCode: String,
    val texts: List<String>,
    val folderId: String
) : Iterable<String> { // Обратите внимание, что Iterable<String>, а не Iterable<TranslateRequest>

    override fun iterator(): Iterator<String> {
        return texts.iterator() // Возвращаем итератор списка texts
    }
}