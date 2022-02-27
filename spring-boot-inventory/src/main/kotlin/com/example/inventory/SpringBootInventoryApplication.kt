package com.example.inventory

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootInventoryApplication

fun main(args: Array<String>) {
	runApplication<SpringBootInventoryApplication>(*args)
}
