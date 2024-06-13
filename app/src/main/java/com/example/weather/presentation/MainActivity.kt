package com.example.weather.presentation

import android.content.Intent
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
        setContent {
            MyComposableContent()
            Thread.sleep(1000L)
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
    }
}

@Composable
fun MyComposableContent() {
    Splash(
        backgourndColor = Color.White,
        onSplashTapped = { },
        producedBy = "A minimal weather app",
        weatherTextContent = stringResource(id = R.string.Weather)
    )
}
