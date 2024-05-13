package com.example.weather.presentation


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.core.content.ContextCompat.startActivity
import com.example.weather.R
import com.example.weather.domain.WeatherViewModel
import com.example.weather.home.Home

class HomeActivity:ComponentActivity() {
    private val viewModel : WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeContent(viewModel, onClickMapping = {
                val intent = Intent(this@HomeActivity, LocationsActivity::class.java)
                startActivity(intent)
            }, onClickSettings = {
                val intent = Intent(this@HomeActivity, DetailsActivity::class.java)
                startActivity(intent)
            })
        }
    }
}

@Composable
fun HomeContent(viewModel: WeatherViewModel,
                onClickMapping: () -> Unit,
                onClickSettings: () -> Unit){
    Home(
        onClickMapping = onClickMapping,
        onClickSettings = onClickSettings,
        locationNow = stringResource(id = R.string.currentLocation),
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