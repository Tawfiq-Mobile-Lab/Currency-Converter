package com.tawfiqdev.currencyconverter.domain

import com.tawfiqdev.currencyconverter.data.ConversionRepository

class ConversionUseCase(private val repo: ConversionRepository) {
    fun execute(amount: Double, from: Currency, to: Currency) = repo.convert(amount, from, to)
}
