package com.example.mobility.vehicle.controller

import com.example.mobility.location.exception.PolygonNotFoundException
import com.example.mobility.vehicle.controller.model.VehicleDto
import com.example.mobility.vehicle.exception.VehicleNotFoundException
import com.example.mobility.vehicle.service.VehicleService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class VehicleController(
    private val vehicleService: VehicleService
) {
    @Operation(
        summary = "Get a list of vehicles based on Polygon Id",
        description = "All vehicles include the polygon Id’s they’re in"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "404", description = "Polygon Id is not available in GeoJson", content = [(Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = PolygonNotFoundException::class)))))]),
            ApiResponse(responseCode = "404", description = "Vehicle is not found in the Polygon Id", content = [(Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = VehicleNotFoundException::class)))))]),
        ]
    )
    @GetMapping("/vehicles/{polygonId}")
    fun findVehicleByPolygonId(@PathVariable("polygonId") polygonId: String): List<VehicleDto> {
        return vehicleService.findVehicleByPolygonId(polygonId).map {
            VehicleDto.from(it)
        }
    }
}