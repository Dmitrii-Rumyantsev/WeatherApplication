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
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.ui.theme.WeatherTheme
import com.google.relay.compose.RelayVector
import com.google.relay.compose.tappable

class SettingsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@Preview
@Composable
fun SettingsContent() {
    WeatherTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 70.dp, start = 30.dp, end = 30.dp, bottom = 30.dp),
                verticalArrangement = Arrangement.Top,
            ) {
                SettingsTop()
                Spacer(modifier = Modifier.height(50.dp))
                SettingTheme()
            }
        }
    }
}

@Composable
fun SettingsTop() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        RelayVector(
            vector = painterResource(R.drawable.locations_vector),
            modifier = Modifier
                .tappable(onTap = {})
                .requiredWidth(15.dp)
                .requiredHeight(15.dp)
                .align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(8.dp)) // Промежуток между вектором и текстом
        Text(
            text = stringResource(id = R.string.selectCity),
            style = MaterialTheme.typography.titleLarge,
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun SettingTheme() {
    Text(
        text = "Theme",
        style = MaterialTheme.typography.bodyLarge,
        fontSize = 24.sp

    )
    Column {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "Dark Theme",
            style = MaterialTheme.typography.titleLarge,
            fontSize = 18.sp

        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "Join the Dark Side!",
            style = MaterialTheme.typography.titleLarge,

        )
    }
    Spacer(modifier = Modifier.height(20.dp))
    Column {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "Light Theme",
            style = MaterialTheme.typography.titleLarge,
            fontSize = 18.sp

        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "Let There be Light!",
            style = MaterialTheme.typography.titleLarge,

            )
    }

}
