package com.example.inventory.controller

import com.example.inventory.service.CarService
import com.example.inventory.utils.CarFaker
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
class CarControllerWebMvcTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var carService: CarService

    @Test
    fun `Given valid url, when GET car list is called, then returns 200 `() {
        val carFakerList = CarFaker.fakeCarList()

        Mockito.`when`(carService.getAllCars()).thenReturn(carFakerList)

        mockMvc.perform(get("/cars")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.[*].id").isNotEmpty)
    }

}