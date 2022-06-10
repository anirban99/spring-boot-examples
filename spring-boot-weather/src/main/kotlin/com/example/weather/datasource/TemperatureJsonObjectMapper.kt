package com.example.weather.datasource

import com.example.weather.configuration.WeatherApiProperties
import com.example.weather.datasource.model.TemperatureJsonResponse
import org.slf4j.LoggerFactory
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import com.example.weather.exception.ClientResponseException
import org.springframework.stereotype.Component

@Component
class TemperatureJsonObjectMapper(private val weatherApiProperties: WeatherApiProperties) {

    private val logger = LoggerFactory.getLogger(TemperatureJsonObjectMapper::class.java)

    fun parseWeatherJson(city: String): TemperatureJsonResponse {
        return try {
            WebClient.create().get().uri(weatherApiProperties.baseUrl+"?q="+city+"&appid="+weatherApiProperties.appKey)
                .retrieve().bodyToFlux(TemperatureJsonResponse::class.java).blockFirst() as TemperatureJsonResponse
        } catch (e: WebClientResponseException) {
            logger.error("Weather API is not responding")
            throw ClientResponseException("Weather API is not responding")
        }
    }
}