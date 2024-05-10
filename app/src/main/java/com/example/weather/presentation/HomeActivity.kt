package com.example.weather.presentation


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import com.example.weather.R
import com.example.weather.domain.WeatherViewModel
import com.example.weather.domain.request.WeatherRequest
import com.example.weather.home.Home

class HomeActivity:ComponentActivity() {
    private val viewModel : WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeContent(viewModel)
        }
    }
}

@Composable
fun HomeContent(viewModel: WeatherViewModel){
    LaunchedEffect(Unit) {
        Log.d("Info", "Start WeatherApi")
        viewModel.fetchWeatherData()
    }
    Home(
        locationNow = stringResource(id = R.string.locationNow),
        currentLocation = stringResource(id = R.string.currentLocation),
        inSync = stringResource(id = R.string.inSync),
        dateNow = stringResource(id = R.string.dateNow),
        tempMax = stringResource(id = R.string.tempMax),
        tempMin = stringResource(id = R.string.tempMin),
        typeWeather = stringResource(id = R.string.typeWeather),
        sunsetTime = stringResource(id = R.string.sunsetTime),
        tempNow = AnnotatedString(stringResource(id = R.string.tempNow)),
        sunriseTime = stringResource(id = R.string.sunriseTime)

    )
}