package com.example.weather.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.ui.theme.WeatherTheme
import com.google.relay.compose.RelayVector
import com.google.relay.compose.tappable

class LocationsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTheme {
                LocationsContent()
            }
        }
    }
}

@Preview
@Composable
fun LocationsContent() {
    WeatherTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 30.dp)
            ) {
                LocationsTop()
                Spacer(modifier = Modifier.height(50.dp))
                LocationsList()
            }
        }
    }
}

@Composable
fun LocationsTop() {
    Row(
        modifier = Modifier
            .fillMaxWidth() // Заполнить всю ширину
            .padding(top = 70.dp, end = 30.dp)
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
        Spacer(modifier = Modifier.weight(1f)) // Заполнить оставшееся пространство
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
        ) {
            RelayVector(
                vector = painterResource(R.drawable.locations_add_location),
                modifier = Modifier
                    .tappable(onTap = {})
                    .requiredWidth(15.dp)
                    .requiredHeight(15.dp)
                    .align(Alignment.CenterEnd),
            )
        }
    }
}


@Composable
fun LocationsList() {
    LazyColumn(
        modifier = Modifier
            .padding(start = 8.dp, end = 30.dp, bottom = 30.dp)
    ) {
        items(400) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f) // Используем вес для равного распределения пространства
                        .height(75.dp)
                ) {
                    Text(
                        text = "Mumbai",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 24.sp, // Уменьшаем размер шрифта
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "22°C",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Light Drizzle",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 12.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterEnd), // Используем Alignment.CenterEnd для выравнивания по правому краю
                        text = "Light cdcds",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 12.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

