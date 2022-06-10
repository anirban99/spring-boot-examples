package com.example.weather.service.model

import java.math.BigDecimal

data class Temperature (
    val celsius: BigDecimal,
    val fahrenheit: BigDecimal,
    val kelvin: BigDecimal
)