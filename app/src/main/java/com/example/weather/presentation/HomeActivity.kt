package com.example.weather.presentation


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import com.example.weather.R
import com.example.weather.data.WeatherCity
import com.example.weather.domain.request.WeatherApi
import com.example.weather.home.Home
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate

class HomeActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("start","start Activity")
        val weatherApi = WeatherApi()
        GlobalScope.launch(Dispatchers.Main) {
            val weather = weatherApi.doKtorRequest()
            Log.d("Weather", weather.toString())
            setContent {
                HomeContent(weather)
            }
        }

    }
}
@Composable
fun HomeContent( weather : WeatherCity){
    Home(
        locationNow = weather.address,
        currentLocation = stringResource(id = R.string.currentLocation),
        inSync = stringResource(id = R.string.inSync),
        dateNow = LocalDate.now().toString(),
        tempMax = weather.days.get(0).tempmax.toString(),
        tempMin = weather.days.get(0).tempmin.toString(),
        typeWeather = stringResource(id = R.string.typeWeather),
        sunsetTime = weather.currentConditions.sunset,
        tempNow = AnnotatedString(weather.days.get(0).temp.toString()),
        sunriseTime = weather.currentConditions.sunrise
    )
//    var weather by remember { mutableStateOf<WeatherCity?>(null) }
//
//    LaunchedEffect(Unit) {
//        Log.d("Info", "Start WeatherApi")
//        viewModel.fetchWeatherData()
//    }
//
//    val weatherState by viewModel.weatherState.collectAsState()
//    weather = weatherState
//
//    Log.d("Info Weather", weather.toString())
//
//    weather?.let { currentWeather ->
//        currentWeather.days.get(0).preciptype.get(0)?.let {
//            Home(
//                locationNow = currentWeather.city,
//                currentLocation = stringResource(id = R.string.currentLocation),
//                inSync = stringResource(id = R.string.inSync),
//                dateNow = currentWeather.currentConditions.dateTime,
//                tempMax = currentWeather.days.get(0).tempmax.toString(),
//                tempMin = currentWeather.days.get(0).tempmin.toString(),
//                typeWeather = it,
//                sunsetTime = currentWeather.days.get(0).sunset,
//                tempNow = AnnotatedString(currentWeather.days.get(0).temp.toString()),
//                sunriseTime = currentWeather.days.get(0).sunrise
//            )
//        }
//    } ?: Home(
//        locationNow = stringResource(id = R.string.locationNow),
//        currentLocation = stringResource(id = R.string.currentLocation),
//        inSync = stringResource(id = R.string.inSync),
//        dateNow = stringResource(id = R.string.dateNow),
//        tempMax = stringResource(id = R.string.tempMax),
//        tempMin = stringResource(id = R.string.tempMin),
//        typeWeather = stringResource(id = R.string.typeWeather),
//        sunsetTime = stringResource(id = R.string.sunsetTime),
//        tempNow = AnnotatedString(stringResource(id = R.string.tempNow)),
//        sunriseTime = stringResource(id = R.string.sunriseTime)
//    )
}