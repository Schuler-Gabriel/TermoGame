package com.schuler.termogame.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.schuler.termogame.R

// Set of Material typography styles to start with

val Inconsolata = FontFamily(
    Font(R.font.inconsolata_extra_light, FontWeight.ExtraLight),
    Font(R.font.inconsolata_light, FontWeight.Light),
    Font(R.font.inconsolata_regular, FontWeight.Normal),
    Font(R.font.inconsolata_medium, FontWeight.Medium),
    Font(R.font.inconsolata_semi_bold, FontWeight.SemiBold),
    Font(R.font.inconsolata_bold, FontWeight.Bold),
    Font(R.font.inconsolata_extra_bold, FontWeight.ExtraBold),
    Font(R.font.inconsolata_black, FontWeight.Black),
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Inconsolata,
        fontWeight = FontWeight.Normal,
        fontSize = 42.sp,
        color = Color.White

    ),
    h1 = TextStyle(
        fontFamily = Inconsolata,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp
    ),
    h2 = TextStyle(
        fontFamily = Inconsolata,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    button = TextStyle(
        fontFamily = Inconsolata,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        color = Color.White
    ),

)