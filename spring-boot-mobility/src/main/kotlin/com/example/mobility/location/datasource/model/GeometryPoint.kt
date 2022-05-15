package com.example.mobility.location.datasource.model

import java.math.BigDecimal

data class GeometryPoint(
    val type: String,
    val coordinates: List<BigDecimal>
)
