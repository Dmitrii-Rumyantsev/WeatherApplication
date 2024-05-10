package com.example.weather.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.weather.R
import com.example.weather.details.Details

class DetailsActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            DetailsContent()
        }
    }
}

@Composable
fun DetailsContent(){
    Details(
        locationNow = stringResource(id = R.string.locationNow),
        currentLocation = stringResource(id = R.string.currentLocation),
        details = stringResource(id = R.string.details),
        precipitation = stringResource(id = R.string.precipitation),
        precipitationScore = "0.0 mm",
        seWind = stringResource(id = R.string.seWind),
        seWindScore = "10.23 km/h",
        humidity = stringResource(id = R.string.humidity),
        humidityScore = "56 %",
        visibility = stringResource(id = R.string.visibility),
        visibilityScore = "14.83 km",
        uv = stringResource(id = R.string.uv),
        uvScore = "Lowest",
        pressure = stringResource(id = R.string.pressure),
        pressureScore = "1012 hPa"
    )
}