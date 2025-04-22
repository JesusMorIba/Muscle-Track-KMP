package com.jmoriba.muscletrack.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    background = LightBackgroundAppColor,
    surface = Grey100Color,
    error = Error,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Grey900Color,
    onSurface = Grey900Color,
    onError = Color.White
)

val DarkColorScheme = darkColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    background = DarkBackgroundAppColor,
    surface = Dark2Color,
    error = Error,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Grey50Color,
    onSurface = Grey50Color,
    onError = Color.White
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors: ColorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}