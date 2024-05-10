package com.example.weather.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.IconButton
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


class ForecastActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            ForecastC()
        }
    }
}

@Composable
fun ForecastC(
    modifier: Modifier = Modifier,
    forecast: String = stringResource(id = R.string.forecast),
    hourlyForecast: String = stringResource(id = R.string.hourlyForecast),
    dailyForecast: String = stringResource(id = R.string.dailyForecast),
    locationNow: String = stringResource(id = R.string.locationNow),
    currentLocation: String = stringResource(id = R.string.currentLocation),
    onClickMapping: () -> Unit = {},
    onClickSettings: () -> Unit = {}
) {
    TopLevel(modifier = modifier) {
        Location(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 30.0.dp,
                    y = 70.0.dp
                )
            )
        ) {
            Mumbai(locationNow = locationNow)
            CurrentLocation(
                currentLocation = currentLocation,
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 0.0.dp,
                        y = 26.0.dp
                    )
                )
            )
        }
        Mapping(
            onClickMapping = onClickMapping,
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 282.875.dp,
                    y = 70.75.dp
                )
            )
        )
        Settings(
            onClickSettings = onClickSettings,
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 323.875.dp,
                    y = 69.875.dp
                )
            )
        )
        Forecast(
            forecast = forecast,
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 30.0.dp,
                    y = 157.0.dp
                )
            )
        )
        HourlyForecast(
            hourlyForecast = hourlyForecast,
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 30.0.dp,
                    y = 235.0.dp
                )
            )
        )
        HourlyLazyRow()
        DailyForecast(
            dailyForecast = dailyForecast,
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 30.0.dp,
                    y = 374.0.dp
                )
            )
        )
        DailyLazyRow()
    }
}

@Preview
@Composable
fun HourlyLazyRow(){
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .size(width = 315.dp, height = 48.dp) // Устанавливаем размер
            .padding(end = 75.dp)
            .offset(x = 30.dp, y = 276.dp) // Сдвигаем по X и Y
    ){
        items(400)

        {
            Text(
                text = "Item $it",
                fontSize = 20.sp, // Уменьшаем размер шрифта
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun DailyLazyRow(){
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .size(width = 315.dp, height = 90.dp) // Устанавливаем размер
            .padding(end = 75.dp)
            .offset(x = 30.dp, y = 415.dp) // Сдвигаем по X и Y
    ){
        items(400)

        {
            Text(
                text = "Itemss $it",
                fontSize = 20.sp, // Уменьшаем размер шрифта
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        }
    }
}

