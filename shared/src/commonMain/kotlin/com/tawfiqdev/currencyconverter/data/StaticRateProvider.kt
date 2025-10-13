package com.tawfiqdev.currencyconverter.data

import com.tawfiqdev.currencyconverter.domain.Currency

class StaticRateProvider : RateProvider {

    private val eur = mapOf(
        Currency.EUR to 1.00, Currency.USD to 1.08, Currency.GBP to 0.86,
        Currency.JPY to 162.5, Currency.CHF to 0.95, Currency.CAD to 1.48,
        Currency.AUD to 1.63, Currency.MAD to 10.6, Currency.CNY to 7.85, Currency.INR to 90.3
    )

    override fun rate(from: Currency, to: Currency): Double {
        if (from == to) return 1.0
        val fromPerEur = 1.0 / (eur[from] ?: error("Unknown currency"))
        val toPerEur = eur[to] ?: error("Unknown currency")
        return toPerEur * fromPerEur
    }
}
