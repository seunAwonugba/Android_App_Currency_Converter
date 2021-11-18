package com.example.currencyconverter.dataclass

data class CurrencyDataClass(
    val base: String,
    val date: String,
    val rates: Rates,
    val success: Boolean,
    val timestamp: Int
)