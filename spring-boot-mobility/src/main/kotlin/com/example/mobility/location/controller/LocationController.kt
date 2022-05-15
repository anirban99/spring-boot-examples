package com.example.mobility.location.controller

import com.example.mobility.location.controller.model.LocationDto
import com.example.mobility.location.exception.PolygonNotFoundException
import com.example.mobility.location.service.LocationService
import com.example.mobility.vehicle.exception.VehicleNotFoundException
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
class LocationController(
    private val locationService: LocationService
) {
    @Operation(
        summary = "Get a polygon based on the VIN of the vehicles",
        description = "Polygons include the VIN of vehicles currently in them"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "404", description = "VIN is not available in Car2Go API for the provided location", content = [(Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = VehicleNotFoundException::class)))))]),
            ApiResponse(responseCode = "404", description = "No Polygon is found containing the VIN", content = [(Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = PolygonNotFoundException::class)))))]),
        ]
    )
    @GetMapping("/locations/{vin}")
    fun findPolygonByVin(@PathVariable("vin") vin: String): LocationDto? {
        return LocationDto.from(locationService.findPolygonByVin(vin))
    }
}