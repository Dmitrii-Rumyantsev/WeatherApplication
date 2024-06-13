package com.example.weather.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.data.WeatherCity
import com.example.weather.ui.theme.WeatherTheme
import com.google.relay.compose.RelayVector
import com.google.relay.compose.tappable


class ForecastActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        val weather: WeatherCity? = intent.getParcelableExtra("Weather")
        setContent {
            WeatherTheme {
                ForecastContent(weather!!,
                    onClickSettings = {navigateToSettings()},
                    onClickHome = {navigateToHome()},
                    onClickDetails = { navigateToDetails(weather) },
                    onClickMapping = { navigateToLocation() })
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

@Composable
fun ForecastContent(
    weather: WeatherCity,
    onClickHome: () -> Unit = {},
    onClickSettings: () -> Unit = {},
    onClickMapping: () -> Unit = {},
    onClickDetails: () -> Unit
) {
    WeatherTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures { change, dragAmount ->
                            when {
                                dragAmount < 0 -> onClickDetails() // Swiped to the left
                            }
                            change.consumePositionChange()
                        }
                    }
                    .clickable {
                        onClickHome()
                    },
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ForecastContentTop(address = weather.address,
                    onClickSettings = onClickSettings,
                    onClickMapping = onClickMapping)
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
                ForecastHourly()
            }
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
            RelayVector(
                vector = painterResource(R.drawable.home_mapping),
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
            RelayVector(
                vector = painterResource(R.drawable.home_settings),
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
fun ForecastHourly() {
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
            items(400)

            {
                Column(
                    modifier = Modifier.width(28.dp)
                ) {
                    Text(
                        text = "11:00",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    RelayVector(
                        vector = painterResource(R.drawable.drizzle),
                        modifier = Modifier
                            .tappable(onTap = {})
                            .requiredWidth(24.dp)
                            .requiredHeight(24.dp)
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
                .size(width = 315.dp, height = 95.dp) // Устанавливаем размер
                .padding(end = 30.dp)
        ) {
            items(400)

            {
                Column(
                    modifier = Modifier.width(35.dp)
                ) {
                    Text(
                        text = "26 Dec",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    RelayVector(
                        vector = painterResource(R.drawable.drizzle),
                        modifier = Modifier
                            .tappable(onTap = {})
                            .requiredWidth(24.dp)
                            .requiredHeight(24.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        RelayVector(
                            vector = painterResource(R.drawable.home_up_array),
                            modifier = Modifier
                                .tappable(onTap = {})
                                .requiredWidth(10.dp)
                                .requiredHeight(15.dp)
                        )
                        Text(
                            text = "26°C",
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 10.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {

                        RelayVector(
                            vector = painterResource(R.drawable.home_down_array),
                            modifier = Modifier
                                .tappable(onTap = {})
                                .requiredWidth(10.dp)
                                .requiredHeight(15.dp)
                        )
                        Text(
                            text = "26°C",
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
