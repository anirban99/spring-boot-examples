package com.example.windpark

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan("com.example.windpark")
class WindParkApplication
//{
//
//    @Bean
//    fun runner(jsonObjectMapper: JsonObjectMapper) = CommandLineRunner {
//        jsonObjectMapper.persistJsonToDatabase()
//    }
//}

fun main(args: Array<String>) {
    runApplication<WindParkApplication>(*args)
}