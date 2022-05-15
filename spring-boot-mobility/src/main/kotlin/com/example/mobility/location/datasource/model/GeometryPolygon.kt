package com.example.mobility.location.datasource.model

import java.math.BigDecimal

data class GeometryPolygon(
    val type: String,
    val coordinates: List<List<List<BigDecimal>>>
)
