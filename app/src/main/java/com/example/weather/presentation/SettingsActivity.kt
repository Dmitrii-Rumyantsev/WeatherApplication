package com.example.weather.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.domain.MainDataBase
import com.example.weather.ui.theme.WeatherTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = MainDataBase.getDataBase(this)
        val isDarkTheme = Themes.isDarkTheme(this)

        setContent {
            WeatherTheme(darkTheme = isDarkTheme) {
                SettingsContent(
                    isDarkTheme,
                    onThemeSwitch = { onThemeSwitch(db, it) },
                    onClickHomeActivity = { navigateToHome() }
                )
            }
        }
    }

    private fun onThemeSwitch(db: MainDataBase, isDarkTheme: Boolean) {
        Themes.setDarkTheme(this, isDarkTheme)
        GlobalScope.launch(Dispatchers.IO) {
            db.getUserDao().updateTheme(1, isDarkTheme)
        }
        recreate() // Пересоздание активности для применения новой темы
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}


@Composable
fun SettingsContent(
    isDarkTheme: Boolean,
    onThemeSwitch: (Boolean) -> Unit,
    onClickHomeActivity: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp, start = 30.dp, end = 30.dp, bottom = 30.dp),
            verticalArrangement = Arrangement.Top,
        ) {
            SettingsTop(onClickHomeActivity)
            Spacer(modifier = Modifier.height(50.dp))
            SettingTheme(isDarkTheme, onThemeSwitch)
        }
    }
}

@Composable
fun SettingsTop(
    onClickHomeActivity: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
                ) {
        Image(
            painter = painterResource(R.drawable.back),
            contentDescription = "Back Button",
            modifier = Modifier
                .requiredWidth(15.dp)
                .requiredHeight(15.dp)
                .align(Alignment.CenterVertically)
                .clickable {
                    onClickHomeActivity()
                }
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
fun SettingTheme(
    isDarkTheme: Boolean,
    onThemeSwitch: (Boolean) -> Unit
) {
    Text(
        text = stringResource(id = R.string.themeName),
        style = MaterialTheme.typography.bodyLarge,
        fontSize = 24.sp
    )
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onThemeSwitch(true) }) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = stringResource(id = R.string.darkName),
            style = MaterialTheme.typography.titleLarge,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = stringResource(id = R.string.joinDark),
            style = MaterialTheme.typography.titleLarge
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onThemeSwitch(false) }) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = stringResource(id = R.string.lightTheme),
            style = MaterialTheme.typography.titleLarge,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = stringResource(id = R.string.joinLight),
            style = MaterialTheme.typography.titleLarge
        )
    }
}
