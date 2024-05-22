package com.beapps.alexaappjetpackcomposeversion.core.presentation

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.beapps.alexaappjetpackcomposeversion.R
import kotlin.random.Random

fun randomGradientColors(): List<Color> {
    val random = Random.Default
    val color1 = Color(
        red = random.nextFloat(),
        green = random.nextFloat(),
        blue = random.nextFloat(),
        alpha = 1f // Set alpha to 1 for full opacity
    )
    val color2 = Color(
        red = random.nextFloat(),
        green = random.nextFloat(),
        blue = random.nextFloat(),
        alpha = 1f // Set alpha to 1 for full opacity
    )
    return listOf(color1, color2)
}

fun randomColor(): Color {
    val random = Random.Default
    val color1 = Color(
        red = random.nextFloat().coerceIn(0.3f, 1f) ,
        green = random.nextFloat().coerceIn(0.3f, 1f) ,
        blue = random.nextFloat().coerceIn(0.3f, 1f) ,
        alpha = random.nextFloat().coerceIn(0.5f , 1f) // Set alpha to 1 for full opacity
    )
    return color1
}


val poppinsFontFamily = FontFamily(
    Font(R.font.poppins_black , FontWeight.Black),
    Font(R.font.poppins_bold , FontWeight.Bold),
    Font(R.font.poppins_light , FontWeight.Light),
    Font(R.font.poppins_medium , FontWeight.Medium),
    Font(R.font.poppins_regular , FontWeight.Normal),
    Font(R.font.poppins_extra_bold , FontWeight.ExtraBold),
    Font(R.font.poppins_extra_light , FontWeight.ExtraLight),
    Font(R.font.poppins_semi_bold , FontWeight.SemiBold),
    Font(R.font.poppins_thin , FontWeight.Thin)
)