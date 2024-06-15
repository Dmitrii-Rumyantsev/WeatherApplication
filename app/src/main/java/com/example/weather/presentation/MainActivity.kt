package com.example.weather.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.lifecycleScope
import com.example.weather.R
import com.example.weather.ui.theme.WeatherTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val db = MainDataBase.getDataBase(this)
//        var user: User? = null
//        GlobalScope.launch(Dispatchers.IO) {
//            user = db.getUserDao().getUser()
//        }
//        if (user == null) {
//            db.getUserDao().insertUser(
//                User(
//                    1, "Moscow",
//                    false, "3"
//                )
//            )
//        }
        val isDarkTheme = Themes.isDarkTheme(this)
        setContent {
            MyComposableContent(isDarkTheme)
        }
        lifecycleScope.launch {
            delay(3000L)
            val intent = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

@Composable
fun MyComposableContent(
    isDarkTheme: Boolean
) {
    WeatherTheme(darkTheme = isDarkTheme) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.Weather),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

