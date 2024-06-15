@file:OptIn(ExperimentalFoundationApi::class)

package com.example.weather.presentation


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.domain.MainDataBase
import com.example.weather.data.WeatherCity
import com.example.weather.data.entity.User
import com.example.weather.domain.api.WeatherApi
import com.example.weather.domain.api.YandexRequest
import com.example.weather.ui.theme.WeatherIcon
import com.example.weather.ui.theme.WeatherTheme
import com.google.relay.compose.RowScopeInstanceImpl.align
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("HomeActivity", "Start Activity")

        val weatherApi = WeatherApi()
        val db = MainDataBase.getDataBase(this)
        val isDarkTheme = Themes.isDarkTheme(this)
        Log.d("HomeActivity", "Theme $isDarkTheme")

        GlobalScope.launch(Dispatchers.IO) {
            val user = db.getUserDao().getUser() ?: run {
                val newUser = User(1, "Moscow", true)
                db.getUserDao().insertUser(newUser)
                newUser
            }

            Log.d("User toString", user.toString())

            val weather = weatherApi.doKtorRequest(user.location_now)
            Log.d("Weather", weather.toString())

            val dateNow = LocalDate.parse(weather.days.first().datetime)
            val date: String
            val cityName: String
            val weatherName: String

            if (Locale.getDefault().language != "en") {
                val yandexRequest = YandexRequest()
                cityName = yandexRequest.doTranslate(Locale.getDefault().language, weather.address.split(" "))
                weatherName = yandexRequest.doTranslate(Locale.getDefault().language, weather.days.first().icon.split("-"))
                Log.d("HomeActivity", weatherName)
                val formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", Locale.getDefault())
                date = dateNow.format(formatter)
            } else {
                val formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", Locale.getDefault())
                date = dateNow.format(formatter)
                cityName = weather.address
                val icon = weather.days.first().icon
                weatherName = icon.split("_").joinToString(" ") { it.capitalize() }
            }

            withContext(Dispatchers.Main) {
                setContent {
                    WeatherTheme {
                        HomeMain(
                            isDarkTheme,
                            weather,
                            cityName,
                            date,
                            weatherName,
                            onClickForecast = { navigateToForecats(weather) },
                            onClickDetails = { navigateToDetails(weather) },
                            onClickMapping = { navigateToLocation() },
                            onClickSettings = { navigateToSettings() }
                        )
                    }
                }
            }
        }
    }
    private fun navigateToDetails(weather: WeatherCity) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("Weather", weather)
        Log.d("HomeActivity",  "Start Activity to Details")
        startActivity(intent)
    }

    private fun navigateToLocation() {
        val intent = Intent(this, LocationsActivity::class.java)
        Log.d("HomeActivity",  "Start Activity to Location")
        startActivity(intent)
    }

    private fun navigateToSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        Log.d("HomeActivity",  "Start Activity to Settings")
        startActivity(intent)
    }

    private fun navigateToForecats(weather: WeatherCity) {
        val intent = Intent(this, ForecastActivity::class.java)
        intent.putExtra("Weather", weather)
        Log.d("HomeActivity",  "Start Activity to Forecast")
        startActivity(intent)
    }
}

@Composable
fun HomeMain(
    isDarkTheme: Boolean,
    weather: WeatherCity,
    cityName: String,
    date: String,
    weatherName: String,
    onClickForecast: () -> Unit = {},
    onClickSettings: () -> Unit = {},
    onClickMapping: () -> Unit = {},
    onClickDetails: () -> Unit
) {
    WeatherTheme(darkTheme = isDarkTheme) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .combinedClickable(
                        onClick = {
                            onClickDetails()
                        },
                        onLongClick = {
                            onClickForecast()
                        }
                    ),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HomeContentTop(
                    cityName,
                    onClickSettings,
                    onClickMapping
                )
                Spacer(modifier = Modifier.height(50.dp))
                HomeContentMiddle(
                    date,
                    weather.days.first().temp.toString(),
                    weather.days.first().tempmin.toString(),
                    weather.days.first().tempmax.toString()
                )
                Spacer(modifier = Modifier.height(50.dp))
                HomeContentMiddleBotton(
                    isDarkTheme,
                    weather.days.first().icon,
                    weatherName
                )
                Spacer(modifier = Modifier.height(50.dp))
                HomeContentBottom(
                    weather.days.first().sunrise,
                    weather.days.first().sunset
                )
            }
        }
    }
}

@Composable
fun HomeContentTop(
    address: String,
    onClickSettings: () -> Unit = {},
    onClickMapping: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 70.dp)
    ) {
        Column(
            modifier = Modifier.padding(start = 30.dp)
        ) {
            Text(
                text = address,
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = stringResource(id = R.string.currentLocation),
                style = MaterialTheme.typography.titleLarge
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 192.dp)
                .clickable { }
        ) {
            Image(
                painter = painterResource(R.drawable.mapping),
                contentDescription = "Mapping Icon",
                modifier = Modifier
                    .clickable {
                        onClickMapping()
                    }
                    .requiredWidth(21.25.dp)
                    .requiredHeight(19.5.dp)
            )
        }
        Column(
            modifier = Modifier.padding(start = 19.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.settings),
                contentDescription = "Settings Icon",
                modifier = Modifier
                    .clickable { onClickSettings() }
                    .size(width = 21.25.dp, height = 19.5.dp)
            )
        }
    }
}

@Composable
fun HomeContentMiddle(
    date: String,
    tempNow: String,
    tempMin: String,
    tempMax: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = R.string.inSync),
                style = MaterialTheme.typography.titleLarge,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = date,
                style = MaterialTheme.typography.titleLarge,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = tempNow,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 96.sp
            )
        }
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally) // Выровнять Row по центру
        ) {
            Image(
                painter = painterResource(R.drawable.down_arrow),
                contentDescription = "Down Arrow",
                modifier = Modifier
                    .requiredWidth(10.dp)
                    .requiredHeight(15.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(

                text = tempMin.dropLast(2),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Image(
                painter = painterResource(R.drawable.up_arrow),
                contentDescription = "Up Arrow",
                modifier = Modifier
                    .requiredWidth(10.dp)
                    .requiredHeight(15.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(

                text = tempMax.dropLast(2),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun HomeContentMiddleBotton(
    isDarkTheme: Boolean,
    typeWeather: String?,
    weatherName: String
) {
    Row {
        if (typeWeather != null) {
            WeatherIcon(iconName = typeWeather,
                modifier = Modifier.width(130.dp).height(170.dp),
                isDarkTheme)
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = weatherName ?: "",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun HomeContentBottom(
    sunrise: String,
    sunset: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterVertically),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.sunset),
            contentDescription = "Sunset Time",
            modifier = Modifier
                .requiredWidth(25.dp)
                .requiredHeight(20.dp)
        )
        Text(

            text = sunrise.substring(0,5),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Image(
            painter = painterResource(R.drawable.sunrise),
            contentDescription = "Sunrise Time",
            modifier = Modifier
                .requiredWidth(25.dp)
                .requiredHeight(20.dp)
        )
        Text(

            text = sunset.substring(0,5),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}
