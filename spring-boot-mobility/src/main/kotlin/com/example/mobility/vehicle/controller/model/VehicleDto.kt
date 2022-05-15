package com.example.mobility.vehicle.controller.model

import com.example.mobility.vehicle.service.model.Vehicle
import com.example.mobility.vehicle.utils.Model

/**
Response DTO for Vehicles
 */
class VehicleDto(
    val polygonId: String,
    val id: Int,
    val locationId: Int,
    val vin: String,
    val numberPlate: String,
    val position: PositionDto,
    val fuel: Double,
    val model: Model
) {
    companion object {
        fun from(from: Vehicle) : VehicleDto {
            return VehicleDto(
                polygonId = from.polygonId,
                id = from.id,
                locationId = from.locationId,
                vin = from.vin,
                numberPlate = from.numberPlate,
                position = PositionDto.from(from.position),
                fuel = from.fuel,
                model = from.model
            )
        }
    }
}