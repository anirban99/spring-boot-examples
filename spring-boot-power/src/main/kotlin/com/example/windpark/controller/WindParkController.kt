package com.example.windpark.controller

import com.example.windpark.controller.model.PowerProducedDto
import com.example.windpark.service.WindParkService
import com.example.windpark.datasource.database.model.WindPark
import com.example.windpark.exception.WindParkUpdateFailedException
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/wind-parks")
class WindParkController(private val windParkService: WindParkService) {

    @Operation(summary = "Get a list of all wind parks")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK")
        ]
    )
    @GetMapping
    fun getAllWindParks(): List<WindPark> = windParkService.getAllWindParks()

    @Operation(summary = "Get the data points of the a wind park by Id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK")
        ]
    )
    @GetMapping("/{id}")
    fun getWindParkById(@PathVariable("id") windParkId: String): WindPark = windParkService.getWindParkById(windParkId)

    @Operation(
        summary = "Update the power produced of a wind park by Id"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK")
        ]
    )
    @PatchMapping("/{id}")
    fun updatePowerProducedByWindPark(@PathVariable("id") windParkId: String, @RequestBody powerProducedDto: PowerProducedDto): WindPark =
        if (powerProducedDto.operation.equals("update", ignoreCase = true)) {
            windParkService.updatePowerProducedByWindParkId(windParkId, powerProducedDto.key, powerProducedDto.value)
        } else throw WindParkUpdateFailedException("Invalid Request")

    @Operation(
        summary = "Delete the data points of a wind park by Id"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK")
        ]
    )
    @DeleteMapping("/{id}")
    fun deleteWindParkById(@PathVariable("id") windParkId: String) = windParkService.deleteWindParkById(windParkId)
}
