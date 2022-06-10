package com.example.weather.controller

import com.example.weather.controller.model.TemperatureResponseDto
import com.example.weather.service.TemperatureService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TemperatureController(private val temperatureService: TemperatureService) {

    @GetMapping("/temperatures/{city}")
    fun getTemperatureByCity(@PathVariable("city") city: String): TemperatureResponseDto {
        return TemperatureResponseDto(
            celsius = temperatureService.getTemperatureByCity(city).celsius,
            fahrenheit = temperatureService.getTemperatureByCity(city).fahrenheit,
            kelvin = temperatureService.getTemperatureByCity(city).kelvin
        )
    }
}