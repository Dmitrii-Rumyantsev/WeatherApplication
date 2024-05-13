package com.example.weather

import com.example.weather.data.WeatherCity
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

fun main(){
    val json = "{\n" +
            "  \"queryCost\": 1,\n" +
            "  \"latitude\": 55.757,\n" +
            "  \"longitude\": 37.615,\n" +
            "  \"resolvedAddress\": \"РњРѕСЃРєРІР°, Р¦РµРЅС‚СЂР°Р»СЊРЅС‹Р№ С„РµРґРµСЂР°Р»СЊРЅС‹Р№ РѕРєСЂСѓРі, Р РѕСЃСЃРёСЏ\",\n" +
            "  \"address\": \"Moscow\",\n" +
            "  \"timezone\": \"Europe/Moscow\",\n" +
            "  \"tzoffset\": 3.0,\n" +
            "  \"days\": [\n" +
            "    {\n" +
            "      \"datetime\": \"2024-05-12\",\n" +
            "      \"datetimeEpoch\": 1715461200,\n" +
            "      \"tempmax\": 7.6,\n" +
            "      \"tempmin\": 2.2,\n" +
            "      \"temp\": 4.6,\n" +
            "      \"feelslikemax\": 6.2,\n" +
            "      \"feelslikemin\": -1.8,\n" +
            "      \"feelslike\": 1.7,\n" +
            "      \"dew\": -2.3,\n" +
            "      \"humidity\": 62.1,\n" +
            "      \"precip\": 0.3,\n" +
            "      \"precipprob\": 6.5,\n" +
            "      \"precipcover\": 12.5,\n" +
            "      \"preciptype\": [\n" +
            "        \"rain\",\n" +
            "        \"snow\"\n" +
            "      ],\n" +
            "      \"snow\": 0.0,\n" +
            "      \"snowdepth\": 0.0,\n" +
            "      \"windgust\": 41.0,\n" +
            "      \"windspeed\": 19.4,\n" +
            "      \"winddir\": 349.1,\n" +
            "      \"pressure\": 1016.8,\n" +
            "      \"cloudcover\": 67.2,\n" +
            "      \"visibility\": 15.8,\n" +
            "      \"solarradiation\": 186.7,\n" +
            "      \"solarenergy\": 16.2,\n" +
            "      \"uvindex\": 5.0,\n" +
            "      \"severerisk\": 10.0,\n" +
            "      \"sunrise\": \"04:23:05\",\n" +
            "      \"sunriseEpoch\": 1715476985,\n" +
            "      \"sunset\": \"20:30:06\",\n" +
            "      \"sunsetEpoch\": 1715535006,\n" +
            "      \"moonphase\": 0.14,\n" +
            "      \"conditions\": \"Partially cloudy\",\n" +
            "      \"description\": \"Partly cloudy throughout the day.\",\n" +
            "      \"icon\": \"partly-cloudy-day\",\n" +
            "      \"stations\": [\n" +
            "        \"UUMO\",\n" +
            "        \"UUWW\",\n" +
            "        \"UUEE\"\n" +
            "      ],\n" +
            "      \"source\": \"comb\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"stations\": {\n" +
            "    \"D3940\": {\n" +
            "      \"distance\": 24961.0,\n" +
            "      \"latitude\": 55.937,\n" +
            "      \"longitude\": 37.853,\n" +
            "      \"useCount\": 0,\n" +
            "      \"id\": \"D3940\",\n" +
            "      \"name\": \"DW3940 Yubileyny RU\",\n" +
            "      \"quality\": 0,\n" +
            "      \"contribution\": 0.0\n" +
            "    },\n" +
            "    \"UUMO\": {\n" +
            "      \"distance\": 29508.0,\n" +
            "      \"latitude\": 55.5,\n" +
            "      \"longitude\": 37.5,\n" +
            "      \"useCount\": 0,\n" +
            "      \"id\": \"UUMO\",\n" +
            "      \"name\": \"UUMO\",\n" +
            "      \"quality\": 50,\n" +
            "      \"contribution\": 0.0\n" +
            "    },\n" +
            "    \"UUWW\": {\n" +
            "      \"distance\": 24702.0,\n" +
            "      \"latitude\": 55.65,\n" +
            "      \"longitude\": 37.27,\n" +
            "      \"useCount\": 0,\n" +
            "      \"id\": \"UUWW\",\n" +
            "      \"name\": \"UUWW\",\n" +
            "      \"quality\": 50,\n" +
            "      \"contribution\": 0.0\n" +
            "    },\n" +
            "    \"UUEE\": {\n" +
            "      \"distance\": 26657.0,\n" +
            "      \"latitude\": 55.97,\n" +
            "      \"longitude\": 37.42,\n" +
            "      \"useCount\": 0,\n" +
            "      \"id\": \"UUEE\",\n" +
            "      \"name\": \"UUEE\",\n" +
            "      \"quality\": 50,\n" +
            "      \"contribution\": 0.0\n" +
            "    }\n" +
            "  },\n" +
            "  \"currentConditions\": {\n" +
            "    \"datetime\": \"13:05:00\",\n" +
            "    \"datetimeEpoch\": 1715508300,\n" +
            "    \"temp\": 4.1,\n" +
            "    \"feelslike\": 0.8,\n" +
            "    \"humidity\": 57.8,\n" +
            "    \"dew\": -3.5,\n" +
            "    \"precip\": 0.0,\n" +
            "    \"precipprob\": 0.0,\n" +
            "    \"snow\": 0.0,\n" +
            "    \"snowdepth\": 0.0,\n" +
            "    \"preciptype\": null,\n" +
            "    \"windgust\": 17.6,\n" +
            "    \"windspeed\": 14.0,\n" +
            "    \"winddir\": 359.0,\n" +
            "    \"pressure\": 1018.0,\n" +
            "    \"visibility\": 10.0,\n" +
            "    \"cloudcover\": 94.5,\n" +
            "    \"solarradiation\": 313.0,\n" +
            "    \"solarenergy\": 1.1,\n" +
            "    \"uvindex\": 3.0,\n" +
            "    \"conditions\": \"Overcast\",\n" +
            "    \"icon\": \"cloudy\",\n" +
            "    \"stations\": [\n" +
            "      \"D3940\",\n" +
            "      \"UUWW\",\n" +
            "      \"UUEE\"\n" +
            "    ],\n" +
            "    \"source\": \"obs\",\n" +
            "    \"sunrise\": \"04:23:05\",\n" +
            "    \"sunriseEpoch\": 1715476985,\n" +
            "    \"sunset\": \"20:30:06\",\n" +
            "    \"sunsetEpoch\": 1715535006,\n" +
            "    \"moonphase\": 0.14\n" +
            "  }\n" +
            "}"


    val weatherCity = Json {ignoreUnknownKeys = true}.decodeFromString<WeatherCity>(json)

    // Теперь у вас есть объект weatherCity, содержащий все данные из JSON
    // Вы можете получить доступ к полям, например:
    println("Город: ${weatherCity.city}")
    println("Текущее время: ${weatherCity.currentConditions.dateTime}")
    println("Максимальная температура на сегодня: ${weatherCity.days.first().tempmax}")

}