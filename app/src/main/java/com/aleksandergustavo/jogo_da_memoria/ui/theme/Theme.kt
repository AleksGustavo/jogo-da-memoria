package com.aleksandergustavo.jogo_da_memoria.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val MemoryColors = lightColorScheme(
    primary = Purple,
    onPrimary = Color.White,
    secondary = Blue,
    tertiary = Orange,
    background = Bg,
    surface = Color.White,
    onBackground = Color(0xFF252231),
    onSurface = Color(0xFF252231)
)

@Composable
fun MemoryGameTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MemoryColors,
        typography = MemoryTypography,
        content = content
    )
}
