package com.example.weather.controller.model

import java.math.BigDecimal

class TemperatureResponseDto (
    val celsius: BigDecimal,
    val fahrenheit: BigDecimal,
    val kelvin: BigDecimal
)