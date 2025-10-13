package com.tawfiqdev.currencyconverter.data

import com.tawfiqdev.currencyconverter.domain.Currency

class ConversionRepository(private val provider: RateProvider) {
    fun convert(amount: Double, from: Currency, to: Currency): ConversionResult {
        val r = provider.rate(from, to)
        val converted = amount * r
        return ConversionResult(converted, r)
    }
}

data class ConversionResult(val amount: Double, val rate: Double)
