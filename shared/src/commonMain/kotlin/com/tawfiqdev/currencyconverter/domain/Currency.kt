package com.tawfiqdev.currencyconverter.domain

enum class Currency(
    val code: String,
    val symbol: String
) {
    EUR("EUR", "€"),
    USD("USD", "$"),
    GBP("GBP", "£"),
    JPY("JPY", "¥"),
    CHF("CHF", "CHF"),
    CAD("CAD", "C$"),
    AUD("AUD", "A$"),
    MAD("MAD", "MAD"),
    CNY("CNY", "¥"),
    INR("INR", "₹");

    companion object {
        fun from(code: String) = entries.first { it.code == code }
    }
}
