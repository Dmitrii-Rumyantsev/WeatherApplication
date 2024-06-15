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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.data.Day
import com.example.weather.data.Hour
import com.example.weather.data.WeatherCity
import com.example.weather.domain.api.YandexRequest
import com.example.weather.ui.theme.WeatherIcon
import com.example.weather.ui.theme.WeatherTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


class ForecastActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Activity", "Start Activity")
        val intent = intent
        val weather: WeatherCity? = intent.getParcelableExtra("Weather")
        var isDarkTheme = Themes.isDarkTheme(this)

        var cityName: String
        GlobalScope.launch{
            if (Locale.getDefault().language != "en") {
                val yandexRequest = YandexRequest()
                cityName = withContext(Dispatchers.IO) {
                    yandexRequest.doTranslate(
                        Locale.getDefault().language,
                        weather?.address!!.split(" ")
                    )
                }
            } else {
                cityName = weather!!.address
            }
            setContent {
                WeatherTheme(darkTheme = isDarkTheme) {
                    ForecastContent(
                        isDarkTheme,
                        weather!!,
                        cityName,
                        onClickSettings = { navigateToSettings() },
                        onClickHome = { navigateToHome() },
                        onClickDetails = { navigateToDetails(weather) },
                        onClickMapping = { navigateToLocation() })
                }
            }
        }
    }

    private fun navigateToDetails(weather: WeatherCity) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("Weather", weather)
        Log.d("Info Activity to Details", "Start Activity to Details")
        startActivity(intent)
    }

    private fun navigateToLocation() {
        val intent = Intent(this, LocationsActivity::class.java)
        Log.d("Info Activity ", "Start Activity to Location")
        startActivity(intent)
    }

    private fun navigateToSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        Log.d("Info Activity ", "Start Activity to Settings")
        startActivity(intent)
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        Log.d("Info Activity ", "Start Activity to Home")
        startActivity(intent)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ForecastContent(
    isDarkTheme: Boolean,
    weather: WeatherCity,
    cityName: String,
    onClickHome: () -> Unit = {},
    onClickSettings: () -> Unit = {},
    onClickMapping: () -> Unit = {},
    onClickDetails: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .combinedClickable(
                    onClick = {
                        onClickDetails()
                    },
                    onLongClick = {
                        onClickHome()
                    }
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ForecastContentTop(
                address = cityName,
                onClickSettings = onClickSettings,
                onClickMapping = onClickMapping
            )
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.forecast),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
            ForecastHourly(
                isDarkTheme,
                weather.days.first().hours,
                weather.days
            )
        }
    }
}

@Composable
fun ForecastContentTop(
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
            modifier = Modifier.padding(start = 192.dp)
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
                painter = painterResource(R.drawable.settings),
                contentDescription = "Settings Icon",
                modifier = Modifier
                    .clickable {
                        onClickSettings()
                    }
                    .requiredWidth(21.25.dp)
                    .requiredHeight(19.5.dp)
            )
        }
    }
}

@Composable
fun ForecastHourly(
    isDarkTheme: Boolean,
    hours: List<Hour>,
    days: List<Day>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp)
    ) {
        Text(
            text = stringResource(id = R.string.hourlyForecast),
            style = MaterialTheme.typography.titleLarge,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .size(width = 315.dp, height = 48.dp) // Устанавливаем размер
                .padding(end = 30.dp)
        ) {
            items(hours.size) { index ->
                val hour = hours[index]
                Column(
                    modifier = Modifier.width(28.dp)
                ) {
                    Text(
                        text = hour.datetime.toString().substring(0, 5),
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    WeatherIcon(
                        iconName = hour.icon,
                        modifier = Modifier
                            .requiredWidth(24.dp)
                            .requiredHeight(24.dp),
                        isDarkTheme
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
            }
        }
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = stringResource(id = R.string.dailyForecast),
            style = MaterialTheme.typography.titleLarge,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .size(width = 315.dp, height = 95.dp)
                .padding(end = 30.dp)
        ) {
            items(days.size) { index ->
                val day = days[index]
                val parsedDate = LocalDate.parse(day.datetime, DateTimeFormatter.ISO_LOCAL_DATE)
                Column(
                    modifier = Modifier.width(35.dp)
                ) {
                    Text(
                        text = "${day.datetime.substring(8)} ${
                            parsedDate.month.toString().substring(0, 3)
                        }",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .requiredWidth(24.dp)
                            .requiredHeight(24.dp)
                            .clipToBounds() // This ensures the image stays within the box
                    ) {
                        WeatherIcon(
                            iconName = day.icon,
                            modifier = Modifier.fillMaxSize(),
                            isDarkTheme
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Image(
                            painter = painterResource(R.drawable.up_arrow),
                            contentDescription = "Up Arrow",
                            modifier = Modifier
                                .requiredWidth(10.dp)
                                .requiredHeight(15.dp)
                        )
                        Text(
                            text = "${day.tempmax.toInt()}°C",
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 10.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Image(
                            painter = painterResource(R.drawable.down_arrow),
                            contentDescription = "Down Arrow",
                            modifier = Modifier
                                .requiredWidth(10.dp)
                                .requiredHeight(15.dp)
                        )
                        Text(
                            text = "${day.tempmin.toInt()}°C",
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 10.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.width(20.dp))
            }

        }
    }
}
