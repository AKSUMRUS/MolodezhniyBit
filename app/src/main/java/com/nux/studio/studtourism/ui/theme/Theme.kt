package com.nux.studio.studtourism.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    background = Black,
    onBackground = Cyan,

    primary = White,
    onPrimary = Black,

    primaryVariant = LightBlack,

    secondary = White,
    onSecondary = Black,

    surface = LightGray,
    onSurface = Black,
)

private val LightColorPalette = lightColors(
    primary = LightLightBlue,
    onPrimary = Black,

    background = White,
    onBackground = Black,

    primaryVariant = LightBlue,

    secondary = LightGray,
    onSecondary = White,

    surface = Black,
    onSurface = Cyan,
)

@Composable
fun StudTourismTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}