package com.example.weather.service;

import com.example.weather.datasource.TemperatureJsonObjectMapper;
import com.example.weather.service.model.Temperature
import org.springframework.stereotype.Service;

@Service
class TemperatureService(private val temperatureJsonObjectMapper: TemperatureJsonObjectMapper) {

    fun getTemperatureByCity(city: String): Temperature {
        val temperatureInKelvin = temperatureJsonObjectMapper.parseWeatherJson(city).main.temp
        return Temperature(
            celsius = temperatureInKelvin.toBigDecimal().minus(273.15.toBigDecimal()),
            fahrenheit = temperatureInKelvin.toBigDecimal().multiply(9.toBigDecimal()).divide(5.toBigDecimal()).minus(459.67.toBigDecimal()),
            kelvin = temperatureInKelvin.toBigDecimal()
        )
    }
}
