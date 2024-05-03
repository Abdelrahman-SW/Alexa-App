package com.beapps.alexaappjetpackcomposeversion.core.presentation

import androidx.compose.ui.graphics.Color
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