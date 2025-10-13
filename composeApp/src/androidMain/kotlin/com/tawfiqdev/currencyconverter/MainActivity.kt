package com.tawfiqdev.currencyconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tawfiqdev.currencyconverter.theme.CurrencyAppTheme
import com.tawfiqdev.currencyconverter.ui.ConverterScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyAppTheme {
                ConverterScreen()
            }
        }
    }
}
