package com.example.inventory.datasource.database.model

import com.example.inventory.utils.Condition
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "car")
data class CarEntity(
    @Id
    val id: Int,
    val make: String,
    val model: String,
    val price: Double,
    @Enumerated(EnumType.STRING)
    val condition: Condition?,
    val discountPrice: String?,
    val date: LocalDate?
)
