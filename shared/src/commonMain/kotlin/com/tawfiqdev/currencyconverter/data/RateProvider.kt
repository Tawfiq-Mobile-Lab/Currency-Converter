package com.tawfiqdev.currencyconverter.data

import com.tawfiqdev.currencyconverter.domain.Currency

interface RateProvider {
    /** returns how many `to` units for 1 `from` unit. */
    fun rate(from: Currency, to: Currency): Double
}
