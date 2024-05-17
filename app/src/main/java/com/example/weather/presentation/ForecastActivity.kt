package com.example.weather.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.forecast.CurrentLocation
import com.example.weather.forecast.DailyForecast
import com.example.weather.forecast.Forecast
import com.example.weather.forecast.HourlyForecast
import com.example.weather.forecast.Location
import com.example.weather.forecast.Mapping
import com.example.weather.forecast.Mumbai
import com.example.weather.forecast.Settings
import com.example.weather.forecast.TopLevel
import com.example.weather.ui.theme.WeatherTheme
import com.google.relay.compose.RelayVector
import com.google.relay.compose.tappable


class ForecastActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            WeatherTheme {
                ForecastContent()
            }
        }
    }
}

@Preview
@Composable
fun ForecastContent(){
    WeatherTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HomeContentTop("Moscow")
                Spacer(modifier = Modifier.height(50.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp)) {
                    Text(
                        text = stringResource(id = R.string.forecast),
                        style = MaterialTheme.typography.bodyLarge)
                }
                Spacer(modifier = Modifier.height(50.dp))
                ForecastHourly()
            }
        }
    }
}

@Composable
fun ForecastHourly(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 30.dp)) {
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
