package com.example.feature.unit

import com.example.feature.datasource.FeatureJsonObjectMapper
import com.example.feature.exception.FeatureNotFoundException
import com.example.feature.exception.ImageNotFoundException
import com.example.feature.exception.JsonFileException
import com.example.feature.service.FeatureService
import com.example.feature.util.FeatureFaker
import io.mockk.every
import io.mockk.mockk
import java.util.UUID
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.util.Base64Utils

class FeatureServiceTest {

    private val featuresJsonObjectMapper: FeatureJsonObjectMapper = mockk()
    private val classUnderTest = FeatureService(featuresJsonObjectMapper)

    @Nested
    inner class FindFeatures {
        @Test
        fun `Given feature collection, When feature is requested, Then should return all features`() {

            val featureCollection = FeatureFaker.fakeFeatureCollection()
            every { featuresJsonObjectMapper.parseGeoJson() } returns featureCollection

            val result = classUnderTest.findFeatures()

            assertEquals(featureCollection.size, result.size)
            assertEquals(featureCollection[0].features[0].properties?.id, result[0].id)
        }

        @Test
        fun `Given empty feature collection, When feature is requested, Then should throw FeatureNotFoundException`() {
            every { featuresJsonObjectMapper.parseGeoJson() } returns emptyList()

            assertThrows<FeatureNotFoundException> { classUnderTest.findFeatures() }
        }

        @Test
        fun `Given exception in parsing feature collection, When feature is requested, Then should throw JsonFileException`() {
            every { featuresJsonObjectMapper.parseGeoJson() } throws JsonFileException("Error parsing Json")

            assertThrows<JsonFileException> { classUnderTest.findFeatures() }
        }
    }

    @Nested
    inner class FindImageByFeatureId {
        @Test
        fun `Given feature collection, When image is requested by feature id which is present in collection, Then should return the image`() {
            val featureId = UUID.fromString("39c2f29e-c0f8-4a39-a98b-deed547d6aea")
            val featureCollection = FeatureFaker.fakeFeatureCollection()
            every { featuresJsonObjectMapper.parseGeoJson() } returns featureCollection

            val result = classUnderTest.findImageByFeatureId(featureId)

            assertEquals(featureCollection[0].features[0].properties?.quicklook, result.convertToString())
        }

        @Test
        fun `Given feature collection, When image is requested by feature id which is not in collection, Then should throw ImageNotFoundException`() {
            val featureId = UUID.fromString("40c2f29e-c0f8-4a39-a98b-deed547d6aea")
            val featureCollection = FeatureFaker.fakeFeatureCollection()
            every { featuresJsonObjectMapper.parseGeoJson() } returns featureCollection

            assertThrows<ImageNotFoundException> { classUnderTest.findImageByFeatureId(featureId) }
        }

        @Test
        fun `Given feature collection containing null quicklook, When image is requested by feature id which is present in collection, Then should throw ImageNotFoundException`() {
            val featureId = UUID.fromString("b0d3bf6a-ff54-49e0-a4cb-e57dcb68d3b5")
            val featureCollection = FeatureFaker.fakeFeatureCollection()
            every { featuresJsonObjectMapper.parseGeoJson() } returns featureCollection

            assertThrows<ImageNotFoundException> { classUnderTest.findImageByFeatureId(featureId) }
        }

        @Test
        fun `Given invalid format in feature collection, When image is requested by feature id which is present in collection, Then should throw IllegalArgumentException`() {
            val featureId = UUID.fromString("ca81d759-0b8c-4b3f-a00a-0908a3ddd655")
            val featureCollection = FeatureFaker.fakeInvalidFeatureCollection()
            every { featuresJsonObjectMapper.parseGeoJson() } returns featureCollection

            assertThrows<IllegalArgumentException> { classUnderTest.findImageByFeatureId(featureId) }
        }

        @Test
        fun `Given empty feature collection, When image is requested by feature id, Then should throw ImageNotFoundException`() {
            val featureId = UUID.fromString("39c2f29e-c0f8-4a39-a98b-deed547d6aea")
            every { featuresJsonObjectMapper.parseGeoJson() } returns emptyList()

            assertThrows<ImageNotFoundException> { classUnderTest.findImageByFeatureId(featureId) }
        }

        @Test
        fun `Given exception in parsing feature collection, When image is requested by feature id, Then should throw JsonFileException`() {
            val featureId = UUID.fromString("39c2f29e-c0f8-4a39-a98b-deed547d6aea")
            every { featuresJsonObjectMapper.parseGeoJson() } throws JsonFileException("Error parsing Json")

            assertThrows<JsonFileException> { classUnderTest.findImageByFeatureId(featureId) }
        }
    }

    private fun ByteArray.convertToString() = Base64Utils.encodeToString(this)
}
