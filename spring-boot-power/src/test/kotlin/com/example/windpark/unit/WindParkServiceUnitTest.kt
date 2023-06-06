package com.example.windpark.unit

import com.example.windpark.datasource.database.repository.WindParkRepository
import com.example.windpark.exception.WindParkNotFoundException
import com.example.windpark.exception.WindParkUpdateFailedException
import com.example.windpark.service.WindParkService
import com.example.windpark.util.WindParkFaker
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class WindParkServiceUnitTest {

    private val windParkRepository: WindParkRepository = mockk()
    private val classUnderTest = WindParkService(windParkRepository)

    @Nested
    inner class GetWindParks {
        @Test
        fun `Given wind parks collection, When get all wind parks is requested, Then should return all wind parks`() {
            val windParksCollection = WindParkFaker.fakeWindParkCollection()
            every { windParkRepository.findAll() } returns windParksCollection

            val result = classUnderTest.getAllWindParks()

            assertEquals(windParksCollection.size, result.size)
            assertEquals(windParksCollection[0].id, result[0].id)
        }

        @Test
        fun `Given empty wind parks collection, When get all wind parks is requested, Then should throw WindParkNotFoundException`() {
            every { windParkRepository.findAll() } returns emptyList()

            assertThrows<WindParkNotFoundException> { classUnderTest.getAllWindParks() }
        }

        @Test
        fun `Given wind parks collection, When get wind park by id is requested, Then should return one wind park`() {
            val windParkId = "aff8c6b2-3919-4847-b41d-9c4182832841"
            val windPark = WindParkFaker.fakeWindPark(id = windParkId)
            every { windParkRepository.findById(any()) } returns Optional.of(windPark)

            val result = classUnderTest.getWindParkById(windParkId)

            assertEquals(windPark.id, result.id)
            assertEquals(windPark.powerProduced, result.powerProduced)
        }

        @Test
        fun `Given wind parks collection, When get wind park by id is requested which is not in collection, Then should throw WindParkNotFoundException`() {
            val windParkId = "0008c6b2-3919-4847-b41d-9c4182832841"
            every { windParkRepository.findById(any()) } returns Optional.empty()

            assertThrows<WindParkNotFoundException> { classUnderTest.getWindParkById(windParkId) }
        }
    }

    @Nested
    inner class UpdateWindParks {
        @Test
        fun`Given wind parks collection, When update power produced by wind park id is requested, Then should return wind park with updated power produced and datetime`() {
            val windParkId = "aff8c6b2-3919-4847-b41d-9c4182832841"
            val updatedPowerProduced = "9.75"
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:00'Z'")
            val updatedDateTime = LocalDateTime.now(ZoneId.of("UTC")).format(formatter)
            val windPark = WindParkFaker.fakeWindPark(id = windParkId)
            val updatedWindPark = WindParkFaker.fakeWindPark(
                id=windParkId,
                powerProduced=updatedPowerProduced.toDouble(),
                updatedOn = updatedDateTime
            )

            every { windParkRepository.findById(any()) } returns Optional.of(windPark)
            every { windParkRepository.save(any()) } returns updatedWindPark

            val result = classUnderTest.updatePowerProducedByWindParkId(
                windParkId = windParkId,
                key = "powerProduced",
                value = updatedPowerProduced
            )

            verify(exactly = 1) { windParkRepository.save(any()) }
            assertEquals(updatedPowerProduced.toDouble(), result.powerProduced)
            assertEquals(updatedDateTime, result.updatedOn)
        }

        @Test
        fun `Given wind parks collection, When update power produced by wind park id is requested which is not in collection, Then should throw WindParkNotFoundException`() {
            val windParkId = "0008c6b2-3919-4847-b41d-9c4182832841"
            val updatedPowerProduced = "9.75"
            val windPark = WindParkFaker.fakeWindPark()

            every { windParkRepository.findById(any()) } returns Optional.empty()
            every { windParkRepository.save(any()) } returns windPark

            verify(exactly = 0) { windParkRepository.save(any()) }

            assertThrows<WindParkNotFoundException> {
                classUnderTest.updatePowerProducedByWindParkId(
                    windParkId = windParkId,
                    key = "powerProduced",
                    value = updatedPowerProduced
                )
            }
        }

        @Test
        fun `Given wind parks collection, When update power produced by wind park id is requested with invalid key, Then should throw WindParkUpdateFailedException`() {
            val windParkId = "aff8c6b2-3919-4847-b41d-9c4182832841"
            val updatedPowerProduced = "9.75"
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:00'Z'")
            val updatedDateTime = LocalDateTime.now(ZoneId.of("UTC")).format(formatter)
            val windPark = WindParkFaker.fakeWindPark(id = windParkId)
            val updatedWindPark = WindParkFaker.fakeWindPark(
                id=windParkId,
                powerProduced=updatedPowerProduced.toDouble(),
                updatedOn = updatedDateTime)

            every { windParkRepository.findById(any()) } returns Optional.of(windPark)
            every { windParkRepository.save(any()) } returns updatedWindPark

            verify(exactly = 0) { windParkRepository.save(any()) }

            assertThrows<WindParkUpdateFailedException> {
                classUnderTest.updatePowerProducedByWindParkId(
                    windParkId = windParkId,
                    key = "period",
                    value = updatedPowerProduced
                )
            }
        }
    }

    @Nested
    inner class DeleteWindParks {
        @Test
        fun `Given wind parks collection, When delete wind park by id is requested, Then should delete one wind park`() {
            val windParkId = "aff8c6b2-3919-4847-b41d-9c4182832841"
            every { windParkRepository.existsById(windParkId) } returns true
            every { windParkRepository.deleteById(windParkId) } just Runs

            classUnderTest.deleteWindParkById(windParkId)

            verify(exactly = 1) { windParkRepository.deleteById(windParkId) }
        }

        @Test
        fun `Given wind parks collection, When delete wind park by id is requested which is not in collection, Then should throw WindParkNotFoundException`() {
            val windParkId = "0008c6b2-3919-4847-b41d-9c4182832841"
            every { windParkRepository.existsById(any()) } returns false

            verify(exactly = 0) { windParkRepository.deleteById(windParkId) }

            assertThrows<WindParkNotFoundException> { classUnderTest.deleteWindParkById(windParkId) }
        }
    }
}

