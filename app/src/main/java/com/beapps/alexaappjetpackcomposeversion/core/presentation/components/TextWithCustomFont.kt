package com.beapps.alexaappjetpackcomposeversion.core.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.beapps.alexaappjetpackcomposeversion.R


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

@Composable
fun TextWithPoppinsFontFamily(modifier: Modifier = Modifier , text : String) {
    Text(
        modifier = modifier ,
        text = text ,
        fontFamily = poppinsFontFamily,
        fontSize = 22.sp,
        textAlign = TextAlign.Center,
        color = Color.White
    )
}