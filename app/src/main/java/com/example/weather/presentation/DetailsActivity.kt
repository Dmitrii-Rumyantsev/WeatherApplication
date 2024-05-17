package com.example.weather.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.data.Day
import com.example.weather.data.WeatherCity
import com.example.weather.details.Details
import com.example.weather.ui.theme.WeatherTheme

class DetailsActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        val weather: WeatherCity? = intent.getParcelableExtra("Weather")
        setContent{
            WeatherTheme {
                DetailsContent(weather!!)

            }
        }
    }
}

@Composable
fun DetailsContent(
    weatherCity: WeatherCity
){
    WeatherTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HomeContentTop(address = weatherCity.address)
                Spacer(modifier = Modifier.height(50.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp)) {
                    Text(
                        text = stringResource(id = R.string.details),
                        style = MaterialTheme.typography.bodyLarge)
                }
                Spacer(modifier = Modifier.height(30.dp))
                DetailsScore(weatherCity.days.first())
            }
        }
    }
}


@Composable
fun DetailsScore(
    day : Day
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp)
    ) {
        Text(
            text = stringResource(id = R.string.precipitation),
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "${day.precip} ${stringResource(id = R.string.precipitationScore)}",
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.seWind),
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "${day.windgust} ${stringResource(id = R.string.seWindScore)}",
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.humidity),
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "${day.humidity} ${stringResource(id = R.string.humidityScore)}",
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.visibility),
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "${day.visibility} ${stringResource(id = R.string.visibilityScore)}",
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.uv),
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "${day.uvindex}",
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.pressure),
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "${day.pressure} ${stringResource(id = R.string.pressureScore)}",
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}
