package com.example.weather.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "weather")
class WeatherApiProperties {
    var baseUrl: String = ""

    var city: String = ""

    var appKey: String = ""
}