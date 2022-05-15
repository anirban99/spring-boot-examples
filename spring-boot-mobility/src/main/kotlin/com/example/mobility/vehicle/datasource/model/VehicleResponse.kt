package com.example.mobility.vehicle.datasource.model


import com.example.mobility.vehicle.utils.Model
import javax.persistence.EnumType
import javax.persistence.Enumerated

data class VehicleResponse (
    val id: Int,
    val locationId: Int,
    val vin: String,
    val numberPlate: String,
    val position: Position,
    val fuel: Double,
    @Enumerated(EnumType.STRING)
    val model: Model
)