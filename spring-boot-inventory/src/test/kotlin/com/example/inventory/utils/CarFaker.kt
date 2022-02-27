package com.example.inventory.utils

import com.example.inventory.datasource.database.model.CarEntity
import com.example.inventory.service.model.Car
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder

class CarFaker {
    companion object {
        private val format = DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-d"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-dd"))
            .toFormatter()

        fun fakeCarList(): List<Car> {
            return listOf(
                fakeCar(),
                fakeCar(
                    id = 76,
                    make = "Toyota",
                    model = "Corolla",
                    finalPrice = 17380.425,
                    condition = Condition.NEW,
                    oldPrice = 18995.00,
                    date = LocalDate.parse("2014-3-12", format)
                ),
                fakeCar(
                    id = 98,
                    make = "Toyota",
                    model = "Corolla",
                    finalPrice = 21200.00,
                    condition = null,
                    oldPrice = null,
                    date = LocalDate.parse("2018-11-9", format)
                )
            )
        }

        private fun fakeCar(
            id: Int = 2,
            make: String = "Mercedes",
            model: String = "S-Class",
            finalPrice: Double = 30600.00,
            condition: Condition? = Condition.NEW,
            oldPrice: Double? = 34000.00,
            date: LocalDate? = LocalDate.parse("2021-03-02", format)
        ): Car {
            return Car(
                id = id,
                make = make,
                model = model,
                finalPrice = finalPrice,
                condition = condition,
                oldPrice = oldPrice,
                date = date
            )
        }

        fun fakeCarEntityList(): List<CarEntity> {
            return listOf(
                fakeCarEntity(),
                fakeCarEntity(
                    id = 76,
                    make = "Toyota",
                    model = "Corolla",
                    price = 18995.00,
                    condition = Condition.NEW,
                    discountPrice = "8.5%",
                    date = LocalDate.parse("2014-3-12", format)
                ),
                fakeCarEntity(
                    id = 98,
                    make = "Toyota",
                    model = "Corolla",
                    price = 21200.00,
                    condition = null,
                    discountPrice = "n/a",
                    date = LocalDate.parse("2018-11-9", format)
                )
            )
        }

        private fun fakeCarEntity(
            id: Int = 2,
            make: String = "Mercedes",
            model: String = "S-Class",
            price: Double = 34000.00,
            condition: Condition? = Condition.NEW,
            discountPrice: String? = "10%",
            date: LocalDate? = LocalDate.parse("2021-03-02", format)
        ): CarEntity {
            return CarEntity(
                id = id,
                make = make,
                model = model,
                price = price,
                condition = condition,
                discountPrice = discountPrice,
                date = date
            )
        }
    }
}