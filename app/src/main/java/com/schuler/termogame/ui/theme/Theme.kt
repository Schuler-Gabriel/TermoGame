package com.schuler.termogame.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.input.key.Key.Companion.W
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Purple,
    primaryVariant = TransparentBlue,
    onPrimary = Blue,
    secondary = Green,
    secondaryVariant = TransparentGreen,
    background = DarkGrey,
    onBackground = Grey,
    onSurface = LightGrey,
    error = TransparentOrage,

)

private val LightColorPalette = lightColors(
    primary = Purple,
    primaryVariant = TransparentBlue,
    onPrimary = Blue,
    secondary = Green,
    secondaryVariant = TransparentGreen,
    background = DarkGrey,
    onBackground = Grey,
    onSurface = LightGrey,
    error = TransparentOrage,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun TermoGameTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = colors.background,
            darkIcons = false
        )
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}