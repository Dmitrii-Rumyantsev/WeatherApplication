package com.example.weather.ui.theme

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weather.R

private val DarkColorScheme = darkColorScheme(
    primary = BackgroundBlack,
    secondary = TextWhite,
    tertiary = TextGrey
)

private val LightColorScheme = lightColorScheme(
    primary = BackgroundWhite,
    secondary = TextBlack,
    tertiary = TextGrey
)

@Composable
fun WeatherTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    Log.d("WeatherTheme", darkTheme.toString())
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val typography = if (darkTheme) DarkTypography else LightTypography

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}
@Composable
fun themedPainterResource(lightResId: Int, darkResId: Int, isDarkTheme: Boolean ): Painter {
    return painterResource(id = if (isDarkTheme) lightResId else darkResId)
}
@Composable
fun WeatherIcon(iconName: String, modifier: Modifier, isDarkTheme: Boolean) {
    val lightResId = when (iconName) {
        "clear_day" -> R.drawable.clear_day
        "clear_night" -> R.drawable.clear_night
        "cloudy" -> R.drawable.cloudy
        "fog" -> R.drawable.fog
        "hail" -> R.drawable.hail
        "partly_cloudy_day" -> R.drawable.partly_cloudy_day
        "partly_cloudy_night" -> R.drawable.partly_cloudy_night
        "rain" -> R.drawable.rain
        "rain_snow" -> R.drawable.rain_snow
        "rain_snow_showers_day" -> R.drawable.rain_snow_showers_day
        "rain_snow_showers_night" -> R.drawable.rain_snow_showers_night
        "showers_day" -> R.drawable.showers_day
        "showers_night" -> R.drawable.showers_night
        "sleet" -> R.drawable.sleet
        "snow" -> R.drawable.snow
        "snow_showers_day" -> R.drawable.snow_showers_day
        "snow_showers_night" -> R.drawable.snow_showers_night
        "thunder" -> R.drawable.thunder
        "thunder_rain" -> R.drawable.thunder_rain
        "thunder_showers_day" -> R.drawable.thunder_showers_day
        "thunder_showers_night" -> R.drawable.thunder_showers_night
        "wind" -> R.drawable.wind
        else -> R.drawable.clear_day
    }

    val darkResId = when (iconName) {
        "clear_day" -> R.drawable.clear_day_dark
        "clear_night" -> R.drawable.clear_night_dark
        "cloudy" -> R.drawable.cloudy_dark
        "fog" -> R.drawable.fog_dark
        "hail" -> R.drawable.hail_dark
        "partly_cloudy_day" -> R.drawable.partly_cloudy_day_dark
        "partly_cloudy_night" -> R.drawable.partly_cloudy_night_dark
        "rain" -> R.drawable.rain_dark
        "rain_snow" -> R.drawable.rain_snow_dark
        "rain_snow_showers_day" -> R.drawable.rain_snow_showers_day_dark
        "rain_snow_showers_night" -> R.drawable.rain_snow_showers_night_dark
        "showers_day" -> R.drawable.showers_day_dark
        "showers_night" -> R.drawable.showers_night_dark
        "sleet" -> R.drawable.sleet_dark
        "snow" -> R.drawable.snow_dark
        "snow_showers_day" -> R.drawable.snow_showers_day_dark
        "snow_showers_night" -> R.drawable.snow_showers_night_dark
        "thunder" -> R.drawable.thunder_dark
        "thunder_rain" -> R.drawable.thunder_rain_dark
        "thunder_showers_day" -> R.drawable.thunder_showers_day_dark
        "thunder_showers_night" -> R.drawable.thunder_showers_night_dark
        "wind" -> R.drawable.wind_dark
        else -> R.drawable.clear_day_dark
    }

    Image(
        painter = themedPainterResource(lightResId, darkResId, isDarkTheme),
        contentDescription = "Weather Icon: $iconName",
        modifier = Modifier
    )
}

