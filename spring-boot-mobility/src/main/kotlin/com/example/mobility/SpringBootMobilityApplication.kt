package com.example.mobility

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class SpringBootMobilityApplication

fun main(args: Array<String>) {
	runApplication<SpringBootMobilityApplication>(*args)
}

