package com.tawfiqdev.currencyconverter.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Purple = Color(0xFF5B2EFF)
private val PurpleDark = Color(0xFF3A1EC7)

@Composable
fun CurrencyAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Purple,
            secondary = PurpleDark,
            surface = Color(0xFFF7F6FB),
            onSurface = Color(0xFF222222)
        ),
        content = content
    )
}
