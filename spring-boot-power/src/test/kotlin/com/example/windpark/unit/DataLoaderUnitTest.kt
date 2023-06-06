package com.example.windpark.unit

import com.example.windpark.configuration.ConfigProperties
import com.example.windpark.datasource.database.repository.WindParkRepository
import com.example.windpark.datasource.mock.DataLoader
import com.example.windpark.exception.JsonFileException
import com.example.windpark.util.WindParkFaker
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DataLoaderUnitTest {
    private val configProperties: ConfigProperties = mockk()
    private val windParkRepository: WindParkRepository = mockk()
    private val classUnderTest = DataLoader(configProperties, windParkRepository)

    @Nested
    inner class LoadListOfObjects {
        @Test
        fun `Given Json file is parsed, When Json file is persisted, Then should save all wind park entries`() {
            val windParksCollection = WindParkFaker.fakeWindParkCollection()
            every { configProperties.fileName } returns "test-data.json"
            every { windParkRepository.deleteAll() } returns Unit
            every { windParkRepository.saveAll(windParksCollection)} returns windParksCollection

            classUnderTest.loadListOfObjects()

            verify(exactly = 1) { windParkRepository.saveAll(windParksCollection) }
        }
    }

    @Nested
    inner class JsonObjectMapper {
        @Test
        fun `Given correct file name, When Json file is parsed, Then should return the list of wind parks`() {

            every { configProperties.fileName } returns "test-data.json"
            val result = classUnderTest.jsonObjectMapper()

            assertEquals(5, result.size)
        }

        @Test
        fun `Given incorrect file name, When Json file is parsed, Then should throw JsonFileException`() {

            every { configProperties.fileName } returns "test-example.json"

            assertThrows<JsonFileException> { classUnderTest.jsonObjectMapper() }
        }

        @Test
        fun `Given input file name is null, When Json file is parsed, Then should throw JsonFileException`() {

            every { configProperties.fileName } returns ""

            assertThrows<JsonFileException> { classUnderTest.jsonObjectMapper() }
        }
    }
}