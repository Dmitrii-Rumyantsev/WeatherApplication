package com.example.weather.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.weather.R
import com.example.weather.splash.Splash

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposableContent()
        }
    }
}

@Composable
fun MyComposableContent() {
    Splash(
        backgourndColor = Color.White,
        onSplashTapped = { /* Действия при нажатии на экран */ },
        producedBy = "A minimal weather app",
        weatherTextContent = stringResource(id = R.string.Weather)
    )
}
