package com.example.inventory.controller.model

import com.example.inventory.utils.Condition
import java.time.LocalDate

data class CarDto(
    val id: Int,
    val make: String,
    val model: String,
    val finalPrice: Double,
    val condition: Condition?,
    val oldPrice: Double?,
    val date: LocalDate?
)
