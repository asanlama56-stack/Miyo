package com.example.miyo.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Theme Colors
private val LightPrimary = Color(0xFF6200EE)
private val LightSecondary = Color(0xFF03DAC6)
private val LightTertiary = Color(0xFF3700B3)

private val DarkPrimary = Color(0xFFBB86FC)
private val DarkSecondary = Color(0xFF03DAC6)
private val DarkTertiary = Color(0xFF3700B3)

private val AmoledPrimary = Color(0xFFBB86FC)
private val AmoledSecondary = Color(0xFF03DAC6)
private val AmoledBackground = Color(0xFF000000)

private val BlueLightPrimary = Color(0xFF2196F3)
private val BlueDarkPrimary = Color(0xFF1565C0)

private val GreenLightPrimary = Color(0xFF4CAF50)
private val GreenDarkPrimary = Color(0xFF388E3C)

private val PurpleLightPrimary = Color(0xFF9C27B0)
private val PurpleDarkPrimary = Color(0xFF6A1B9A)

private val OrangeLightPrimary = Color(0xFFFF9800)
private val OrangeDarkPrimary = Color(0xFFE65100)

private val RedLightPrimary = Color(0xFFF44336)
private val RedDarkPrimary = Color(0xFFC62828)

fun getLightColorScheme(primary: Color = LightPrimary, secondary: Color = LightSecondary): ColorScheme {
    return lightColorScheme(
        primary = primary,
        secondary = secondary,
        tertiary = LightTertiary,
    )
}

fun getDarkColorScheme(primary: Color = DarkPrimary, secondary: Color = DarkSecondary): ColorScheme {
    return darkColorScheme(
        primary = primary,
        secondary = secondary,
        tertiary = DarkTertiary,
    )
}

fun getAmoledColorScheme(): ColorScheme {
    return darkColorScheme(
        primary = AmoledPrimary,
        secondary = AmoledSecondary,
        tertiary = DarkTertiary,
        background = AmoledBackground,
        surface = Color.Black,
    )
}
