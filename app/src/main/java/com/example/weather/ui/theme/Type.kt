package com.example.weather.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.weather.home.ubuntuCondensed

// Set of Material typography styles to start with
val LightTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = ubuntuCondensed,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        letterSpacing = 0.5.sp,
        color = TextBlack
    ),
    titleLarge = TextStyle(
        fontFamily = ubuntuCondensed,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        letterSpacing = 0.5.sp,
        color = TextGrey
    )
)

val DarkTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = ubuntuCondensed,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        letterSpacing = 0.5.sp,
        color = TextWhite
    ),
    titleLarge = TextStyle(
        fontFamily = ubuntuCondensed,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        letterSpacing = 0.5.sp,
        color = TextGrey
    )
)