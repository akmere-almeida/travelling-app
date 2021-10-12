package com.akmere.travelling_app.presentation.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val lightThemeColors = lightColors(
    primary = Color(AppColorCodes.PartialWhite),
    primaryVariant = Color(AppColorCodes.PartialWhiteDark),
    secondary = Color(AppColorCodes.DarkBlue),
    secondaryVariant = Color(AppColorCodes.LightBlue),
    background = Color(AppColorCodes.PartialWhiteDark),
    surface = Color(AppColorCodes.PartialWhite),
    error = Color(AppColorCodes.PartialRed),
    onPrimary = Color.White,
    onSecondary = Color(AppColorCodes.Blue),
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.Black
)

val darkThemeColors = darkColors(
    primary = Color(AppColorCodes.PartialBlack),
    primaryVariant = Color(AppColorCodes.DarkBlue),
    secondary = Color(AppColorCodes.Blue),
    secondaryVariant = Color(AppColorCodes.LightBlue),
    background = Color(AppColorCodes.PartialBlack),
    surface = Color.Black,
    error = Color(AppColorCodes.PartialRed),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.Black
)

@SuppressWarnings
@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val colors = if (isSystemInDarkTheme()) {
        darkThemeColors
    } else {
        lightThemeColors
    }

    MaterialTheme(
        colors = colors,
        typography = appTypography,
        content = content
    )
}
