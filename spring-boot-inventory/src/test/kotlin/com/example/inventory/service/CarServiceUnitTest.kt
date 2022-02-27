package com.example.inventory.service

import com.example.inventory.datasource.database.repository.CarRepository
import com.example.inventory.utils.CarFaker
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class CarServiceUnitTest {

    private var carRepository = mock<CarRepository>{}
    private val classUnderTest = CarService(carRepository)

    @Test
    fun `Using Mockito Given inventory list, When cars are requested, Then returns list of cars`() {
        val carFakerEntityList = CarFaker.fakeCarEntityList()
        val carFaker = CarFaker.fakeCarList()

        `when`(carRepository.findAll()).thenReturn(carFakerEntityList)

        val result = classUnderTest.getAllCars()

        assertEquals(carFakerEntityList.size, result.size)
    }
}