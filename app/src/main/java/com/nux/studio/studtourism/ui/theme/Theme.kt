package com.nux.studio.studtourism.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
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

    error = Red,
)

@Composable
fun StudTourismTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}