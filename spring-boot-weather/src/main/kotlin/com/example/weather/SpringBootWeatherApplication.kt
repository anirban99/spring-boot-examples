package com.example.weather

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootWeatherApplication

fun main(args: Array<String>) {
	runApplication<SpringBootWeatherApplication>(*args)
}
