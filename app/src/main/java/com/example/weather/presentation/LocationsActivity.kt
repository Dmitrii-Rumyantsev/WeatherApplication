package com.example.weather.presentation;

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.SwipeToDismissBox
import com.example.weather.R
import com.example.weather.domain.MainDataBase
import com.example.weather.data.WeatherCity
import com.example.weather.data.entity.Weather
import com.example.weather.domain.api.WeatherApi
import com.example.weather.domain.api.YandexRequest
import com.example.weather.ui.theme.WeatherIcon
import com.example.weather.ui.theme.WeatherTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale
import kotlin.reflect.KFunction1

class LocationsActivity : ComponentActivity() {

    private var weatherCityList: MutableList<WeatherCity> = mutableListOf()
    private var cityName = mutableListOf<String>()
    private var weatherName = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("LocationsActivity", "Start Activity")

        val weatherApi = WeatherApi()
        val db = MainDataBase.getDataBase(this)
        var isDarkTheme = Themes.isDarkTheme(this)
        GlobalScope.launch(Dispatchers.IO) {
            val id = 1
            var weathers: MutableList<String> = db.getWeatherDao().getAllLocations()

            if (weathers.isEmpty()) {
                val cityName = db.getUserDao().getUser()?.location_now
                if (cityName != null) {
                    db.getWeatherDao().insertWeather(Weather(userId = 1, location = cityName))
                    weathers.add(cityName)
                }
            }

            weatherCityList = weatherApi.getWeatherCities(weathers).toMutableList()

            for (x in weatherCityList) {
                if (Locale.getDefault().language != "en") {
                    val yandexRequest = YandexRequest()
                    val translatedCity = withContext(Dispatchers.IO) {
                        yandexRequest.doTranslate(
                            Locale.getDefault().language,
                            x.address.split(" ")
                        )
                    }
                    cityName.add(translatedCity)
                    Log.d("Weather", cityName.toString())
                    val translatedWeather = withContext(Dispatchers.IO) {
                        yandexRequest.doTranslate(
                            Locale.getDefault().language,
                            x.days.first().icon.split("-")
                        )
                    }
                    weatherName.add(translatedWeather)
                } else {
                    cityName.add(x.address)
                    val icon = x.days.first().icon
                    val translatedWeather =
                        icon.split("_").joinToString(" ") { it.capitalize() }.replace("-", " ")
                    weatherName.add(translatedWeather)
                }
            }
            withContext(Dispatchers.Main) {
                setContent {
                    WeatherTheme(darkTheme = isDarkTheme) {
                        LocationsContent(
                            isDarkTheme,
                            cityName = cityName,
                            weatherName = weatherName,
                            weatherCityList,
                            ::onCityAdded,
                            ::onDeleteWeatherCity,
                            ::onCitySelected
                        ) { navigateToHome() }
                    }
                }
            }
        }
    }

    private fun onCityAdded(newCityName: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = MainDataBase.getDataBase(this@LocationsActivity)
            var isDarkTheme = Themes.isDarkTheme(this@LocationsActivity)
            db.getUserDao().updateLocationNow(1, newCityName)
            val weatherNull = db.getWeatherDao().getWeatherByLocation(newCityName)
            if (weatherNull == null) {
                db.getWeatherDao().insertWeather(Weather(id = 0, userId = 1, newCityName))

                val weatherApi = WeatherApi()
                weatherCityList.add(weatherApi.doKtorRequest(newCityName))

                withContext(Dispatchers.Main) {
                    setContent {
                        WeatherTheme(darkTheme = isDarkTheme) {
                            LocationsContent(
                                isDarkTheme,
                                cityName = cityName,
                                weatherName = weatherName,
                                weatherCityList,
                                ::onCityAdded,
                                ::onDeleteWeatherCity,
                                ::onCitySelected
                            ) { navigateToHome() }
                        }
                    }
                }
            } else {
                Log.d("LocationsActivity", "This city in table")
            }
        }
    }

    private fun onCitySelected(weatherCity: WeatherCity) {
        GlobalScope.launch(Dispatchers.IO) {
            val db = MainDataBase.getDataBase(this@LocationsActivity)
            db.getUserDao().updateLocationNow(1, weatherCity.address)
            var isDarkTheme = Themes.isDarkTheme(this@LocationsActivity)

            withContext(Dispatchers.Main) {
                setContent {
                    WeatherTheme(darkTheme = isDarkTheme) {
                        LocationsContent(
                            isDarkTheme,
                            cityName = cityName,
                            weatherName = weatherName,
                            weatherCityList,
                            ::onCityAdded,
                            ::onDeleteWeatherCity,
                            ::onCitySelected
                        ) { navigateToHome() }
                    }
                }
            }
        }
    }

    private fun onDeleteWeatherCity(weatherCity: WeatherCity) {
        GlobalScope.launch(Dispatchers.IO) {
            val db = MainDataBase.getDataBase(this@LocationsActivity)
            db.getWeatherDao().deleteWeatherByLocation(weatherCity.address)
            var isDarkTheme = Themes.isDarkTheme(this@LocationsActivity)

            weatherCityList.remove(weatherCity)

            withContext(Dispatchers.Main) {
                setContent {
                    WeatherTheme(darkTheme = isDarkTheme) {
                        LocationsContent(
                            isDarkTheme,
                            cityName = cityName,
                            weatherName = weatherName,
                            weatherCityList,
                            ::onCityAdded,
                            ::onDeleteWeatherCity,
                            ::onCitySelected
                        ) { navigateToHome() }
                    }
                }
            }
        }
    }


    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        Log.d("Info Activity ", "Start Activity to Home")
        startActivity(intent)
    }
}

@Composable
fun LocationsContent(
    isDarkTheme: Boolean,
    cityName: MutableList<String>,
    weatherName: MutableList<String>,
    weatherList: List<WeatherCity>,
    onCityAdded: KFunction1<String, Unit>,
    onDeleteWeatherCity: (WeatherCity) -> Unit,
    onCitySelected: KFunction1<WeatherCity, Unit>,
    onClickHome: () -> Unit

) {
    var weatherCity by remember { mutableStateOf(weatherList.toMutableList()) }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 30.dp)
        ) {
            LocationsTop(onCityAdded, onClickHome)
            Spacer(modifier = Modifier.height(50.dp))
            LocationsList(
                isDarkTheme,
                weatherName, cityName,
                weatherCity, onDeleteWeatherCity, onCitySelected
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationsTop(
    onCityAdded: (String) -> Unit,
    onClickHome: () -> Unit
) {
    var isDialogOpen by remember { mutableStateOf(false) }
    var cityName by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 70.dp, end = 30.dp)
    ) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier.clickable { onClickHome() }) {
            Image(
                painter = painterResource(R.drawable.back),
                contentDescription = "Back Button",
                modifier = Modifier
                    .requiredWidth(15.dp)
                    .requiredHeight(15.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(id = R.string.selectCity),
            style = MaterialTheme.typography.titleLarge,
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(modifier = Modifier
            .align(Alignment.CenterVertically)
            .clickable {
                isDialogOpen = true
            }) {
            Image(
                painter = painterResource(R.drawable.add),
                contentDescription = "Add Button",
                modifier = Modifier
                    .requiredWidth(15.dp)
                    .requiredHeight(15.dp)
            )
        }
    }

    if (isDialogOpen) {
        AlertDialog(onDismissRequest = {
            isDialogOpen = false
            cityName = ""
        }, title = {
            Text(text = "Enter City Name")
        }, text = {
            Column {
                TextField(value = cityName,
                    onValueChange = { cityName = it },
                    label = { Text("City Name") })
            }
        }, confirmButton = {
            Button(onClick = {
                if (cityName.isNotBlank()) {
                    onCityAdded(cityName)
                    isDialogOpen = false
                    cityName = ""
                }
            }) {
                Text(text = "Confirm")
            }
        }, dismissButton = {
            Button(onClick = {
                isDialogOpen = false
                cityName = ""
            }) {
                Text(text = "Cancel")
            }
        })
    }
}

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun LocationsList(
    isDarkTheme: Boolean,
    cityName: MutableList<String>,
    weatherName: MutableList<String>,
    weatherList: List<WeatherCity>,
    onDeleteWeatherCity: (WeatherCity) -> Unit,
    onCitySelected: KFunction1<WeatherCity, Unit>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 30.dp, bottom = 30.dp)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        items(weatherList.size) { index ->
            val weatherCity = weatherList[index]
            Log.d("Info", cityName.toString())
            Log.d("Info", weatherName.toString())

            Box(
                modifier =
                Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable(onClick = { onCitySelected(weatherCity) })
            ) {
                SwipeToDismissBox(
                    onDismissed = { onDeleteWeatherCity(weatherCity) },
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primary)
                            .clickable { onCitySelected(weatherCity) }
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .height(75.dp)
                        ) {
                            Text(
                                text = weatherName.get(index),
                                style = MaterialTheme.typography.bodyLarge,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.secondary // Используем цветовую схему
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "${weatherCity.days.first().temp}°C",
                                style = MaterialTheme.typography.titleLarge,
                                fontSize = 18.sp
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = cityName.get(index),
                                style = MaterialTheme.typography.titleLarge,
                                fontSize = 12.sp
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .align(Alignment.CenterVertically)
                        ) {
                            WeatherIcon(
                                iconName = weatherCity.days.first().icon,
                                modifier = Modifier.align(Alignment.CenterEnd),
                                isDarkTheme
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }

    }
}
