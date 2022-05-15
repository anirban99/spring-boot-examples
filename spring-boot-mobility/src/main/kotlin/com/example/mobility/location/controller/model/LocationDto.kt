package com.example.mobility.location.controller.model

import com.example.mobility.location.service.model.Location

/**
Response DTO for Locations
 */
data class LocationDto(
    val polygonId: String,
    val vin: String
) {
    companion object ModelMapper {
        fun from(from: Location): LocationDto {
            return LocationDto(
                polygonId = from.polygonId,
                vin = from.vin
            )
        }
    }
}