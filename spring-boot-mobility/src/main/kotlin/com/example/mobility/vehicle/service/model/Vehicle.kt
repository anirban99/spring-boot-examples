package com.example.mobility.vehicle.service.model

import com.example.mobility.vehicle.utils.Model
import javax.persistence.EnumType
import javax.persistence.Enumerated

data class Vehicle(
    val polygonId: String,
    val id: Int,
    val locationId: Int,
    val vin: String,
    val numberPlate: String,
    val position: PositionDetails,
    val fuel: Double,
    @Enumerated(EnumType.STRING)
    val model: Model
)