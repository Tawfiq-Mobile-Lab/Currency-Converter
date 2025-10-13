package com.tawfiqdev.currencyconverter.util

import com.tawfiqdev.currencyconverter.domain.Currency
import kotlin.math.round

fun formatAmount(value: Double, c: Currency): String {
    val rounded = round(value * 100.0) / 100.0
    return when (c) {
        Currency.USD -> "$ - $rounded"
        Currency.EUR -> "€ - $rounded"
        Currency.GBP -> "£ - $rounded"
        Currency.JPY, Currency.CNY -> "¥ - $rounded"
        Currency.INR -> "₹ - $rounded"
        Currency.CHF -> "CHF - $rounded"
        Currency.CAD -> "C$ - $rounded"
        Currency.AUD -> "A$ - $rounded"
        Currency.MAD -> "MAD - $rounded"
    }
}
