package com.example.weather.presentation


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import com.example.weather.data.WeatherCity
import com.example.weather.domain.request.WeatherApi
import com.example.weather.ui.theme.WeatherTheme
import com.google.relay.compose.RelayVector
import com.google.relay.compose.RowScopeInstanceImpl.align
import com.google.relay.compose.tappable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("start", "start Activity")
        val weatherApi = WeatherApi()
        GlobalScope.launch(Dispatchers.Main) {
            val weather = weatherApi.doKtorRequest()
            Log.d("Weather", weather.toString())
            setContent {
                WeatherTheme {
                    HomeMain(weather,onClickDetails = { navigateToDetails(weather) },
                        onClickMapping = { navigateToLocation()},
                        onClickSettings = {navigateToSettings()})
                }
            }
        }

    }

    private fun navigateToDetails(weather: WeatherCity){
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("Weather",weather)
        Log.d("Info Activity to Details","Start Activity to Details")
        startActivity(intent)
    }
    private fun navigateToLocation(){
        val intent = Intent(this, LocationsActivity::class.java)
        Log.d("Info Activity ","Start Activity to Location")
        startActivity(intent)
    }
    private fun navigateToSettings(){
        val intent = Intent(this, SettingsActivity::class.java)
        Log.d("Info Activity ","Start Activity to Settings")
        startActivity(intent)
    }
}

@Composable
fun HomeMain(
    weather: WeatherCity,
    onClickSettings: () -> Unit = {},
    onClickMapping: () -> Unit = {},
    onClickDetails: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
                .clickable{
                    onClickDetails()
                },
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeContentTop(weather.address, onClickDetails,
                onClickMapping)
            Spacer(modifier = Modifier.height(50.dp))
            HomeContentMiddle(
                weather.days.first().temp.toString(),
                weather.days.first().tempmin.toString(),
                weather.days.first().tempmax.toString()
            )
            Spacer(modifier = Modifier.height(50.dp))
            HomeContentMiddleBotton(
                weather.days.first().icon
            )
            Spacer(modifier = Modifier.height(50.dp))
            HomeContentBottom(
                weather.days.first().sunrise,
                weather.days.first().sunset
            )
        }
    }
}

@Composable
fun HomeContentTop(
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
                    .clickable{
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
                    .clickable{
                        onClickSettings()
                    }
                    .requiredWidth(21.25.dp)
                    .requiredHeight(19.5.dp)
            )
        }
    }
}

@Composable
fun HomeContentMiddle(
    dateNow:String,
    tempMin:String,
    tempMax:String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = R.string.inSync),
                style = MaterialTheme.typography.titleLarge,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = R.string.dateNow),
                style = MaterialTheme.typography.titleLarge,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = dateNow,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 96.sp
            )
        }
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally) // Выровнять Row по центру
        ) {
            RelayVector(
                vector = painterResource(R.drawable.home_down_array),
                modifier = Modifier
                    .requiredWidth(10.dp)
                    .requiredHeight(15.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(

                text = tempMin,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(20.dp))
            RelayVector(
                vector = painterResource(R.drawable.home_up_array),
                modifier = Modifier
                    .requiredWidth(10.dp)
                    .requiredHeight(15.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(

                text = tempMax,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun HomeContentMiddleBotton(
    typeWeather:String?
) {
    Row {
        RelayVector(
            vector = painterResource(R.drawable.home_type_weather),
            modifier = Modifier
                .requiredWidth(130.dp)
                .requiredHeight(130.dp)
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
    Text(

        text = typeWeather!!,
        style = MaterialTheme.typography.titleLarge,
        fontSize = 18.sp,
        modifier = Modifier.align(Alignment.CenterVertically)
    )
}

@Composable
fun HomeContentBottom(
    sunrise:String,
    sunset:String
) {
    Row(
        modifier = Modifier.align(Alignment.CenterVertically) // Выровнять Row по центру
    ) {
        RelayVector(
            vector = painterResource(R.drawable.home_sunset_svg),
            modifier = Modifier
                .requiredWidth(25.dp)
                .requiredHeight(20.dp)
        )
        Text(

            text = sunrise,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(20.dp))
        RelayVector(
            vector = painterResource(R.drawable.home_sunrise_svg),
            modifier = Modifier
                .requiredWidth(25.dp)
                .requiredHeight(20.dp)
        )
        Text(

            text = sunset,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}