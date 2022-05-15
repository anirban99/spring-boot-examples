package com.example.mobility.vehicle.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "car2go")
class Car2GoApiProperties {
    var baseUrl: String = ""

    var vehicle: String = ""

    var locationName: String = ""

    var duration: String = ""
}
