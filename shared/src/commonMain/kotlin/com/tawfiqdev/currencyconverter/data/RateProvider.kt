package com.tawfiqdev.currencyconverter.data

import com.tawfiqdev.currencyconverter.domain.Currency

interface RateProvider {
    fun rate(from: Currency, to: Currency): Double

}
