package com.example.feature.unit

import com.example.feature.configuration.ConfigProperties
import com.example.feature.datasource.FeatureJsonObjectMapper
import com.example.feature.exception.JsonFileException
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class FeatureJsonObjectMapperTest {

    private val configProperties: ConfigProperties = mockk()
    private val classUnderTest = FeatureJsonObjectMapper(configProperties)

    @Test
    fun `Given correct file name, When Json file is parsed, Then should return the list of features`() {

        every { configProperties.fileName } returns "source-data-test.json"
        val result = classUnderTest.parseGeoJson()

        assertEquals(2, result.size)
    }

    @Test
    fun `Given incorrect file name, When Json file is parsed, Then should throw JsonFileException`() {

        every { configProperties.fileName } returns "source-data-example.json"

        assertThrows<JsonFileException> { classUnderTest.parseGeoJson() }
    }

    @Test
    fun `Given input file name is null, When Json file is parsed, Then should throw JsonFileException`() {

        every { configProperties.fileName } returns ""

        assertThrows<JsonFileException> { classUnderTest.parseGeoJson() }
    }
}
