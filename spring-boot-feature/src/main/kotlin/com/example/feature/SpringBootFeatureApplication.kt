package com.example.feature

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan("com.example.feature")
class SpringBootFeatureApplication

fun main(args: Array<String>) {
    runApplication<SpringBootFeatureApplication>(*args)
}
