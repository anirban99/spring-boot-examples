package com.example.mobility.vehicle.controller.model

import com.example.mobility.vehicle.service.model.PositionDetails
import java.math.BigDecimal

/**
Response DTO for Position
 */
class PositionDto (
    val longitude: BigDecimal,
    val latitude: BigDecimal
) {
    companion object ModelMapper {
        fun from(from: PositionDetails): PositionDto {
            return PositionDto(
                longitude = from.longitude,
                latitude = from.latitude
            )
        }
    }
}