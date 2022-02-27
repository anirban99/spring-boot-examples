package com.example.inventory.service

import com.example.inventory.datasource.database.repository.CarRepository
import com.example.inventory.service.model.Car
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CarService(private val carRepository: CarRepository) {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun getAllCars(): List<Car> {
        logger.info("Fetch from repository: " + carRepository.findAll())
        //TODO call the getDiscountPrice() function once
        val carList = carRepository.findAll().map {
            Car(
                id = it.id,
                make = it.make,
                model = it.model,
                finalPrice = it.discountPrice?.let { value -> getDiscountPrice(it.price, value) } ?: it.price,
                condition = it.condition,
                oldPrice = if (it.discountPrice?.let { value ->
                        getDiscountPrice(it.price, value) } != null) it.price
                        else null,
                date = it.date
            )
        }
        logger.info("Return car List: $carList")
        return carList
    }

    private fun getDiscountPrice(originalPrice: Double, discountPrice: String): Double? {

        if (discountPrice.contains("n/a")) return null
        else if (discountPrice.contains("%")) {
            val discountAmount: Double = (discountPrice.substringBefore('%').toDouble() / 100) * originalPrice
            return originalPrice - discountAmount
        } else if (!discountPrice.contains("%")) return discountPrice.toDouble()

        return null
    }
}