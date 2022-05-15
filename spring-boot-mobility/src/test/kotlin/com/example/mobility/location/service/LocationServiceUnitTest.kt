package com.example.mobility.location.service

import com.example.mobility.location.datasource.PolygonClient
import com.example.mobility.location.exception.PolygonNotFoundException
import com.example.mobility.location.utils.PolygonUtils
import com.example.mobility.utils.faker.LocationFaker
import com.example.mobility.utils.faker.VehicleFaker
import com.example.mobility.vehicle.datasource.VehicleClient
import com.example.mobility.vehicle.exception.VehicleNotFoundException
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.client.WebClientResponseException

class LocationServiceUnitTest {

    private val vehicleClient: VehicleClient = mockk()
    private val polygonClient: PolygonClient = mockk()
    private val polygonUtils: PolygonUtils = mockk()
    private val classUnderTest = LocationService(vehicleClient, polygonClient, polygonUtils)

    @Test
    fun `Given polygon geojson, When polygon is requested by valid VIN, Then returns polygon`() {
        val vin = "WAUSH54F08AAS7GT5"

        every { vehicleClient.getVehicles() } returns VehicleFaker.fakeVehicleResponseList()
        every { polygonClient.getPolygon() } returns LocationFaker.fakePolygonResponse()
        every { polygonUtils.getPolygonCoordinates(any()) } returns listOf()
        every { polygonUtils.isPositionInPolygon(any(), any(), any()) } returns true

        val result = classUnderTest.findPolygonByVin(vin)

        assertEquals(vin, result.vin)
    }

    @Test
    fun `Given polygon geojson, When polygon is requested by valid VIN but Polygon client has exception, Then throws exception`() {
        val vin = "WAUSH54F08AAS7GT5"

        every { vehicleClient.getVehicles() } returns VehicleFaker.fakeVehicleResponseList()
        every { polygonClient.getPolygon() } throws PolygonNotFoundException(
            HttpStatus.NOT_FOUND,
            "Error while converting GeoJson to Object"
        )
        every { polygonUtils.getPolygonCoordinates(any()) } returns listOf()
        every { polygonUtils.isPositionInPolygon(any(), any(), any()) } returns true

        assertThrows<PolygonNotFoundException> { classUnderTest.findPolygonByVin(vin) }
    }

    @Test
    fun `Given vehicle list, When vehicle is requested by valid polygon Id but Vehicle client has exception, Then throws exception`() {
        val vin = "WAUSH54F08AAS7GT5"

        every { vehicleClient.getVehicles() } throws WebClientResponseException(
            404,
            "Car2go Vehicle API cannot establish connection",
            null,
            null,
            null
        )
        every { polygonClient.getPolygon() } returns LocationFaker.fakePolygonResponse()
        every { polygonUtils.getPolygonCoordinates(any()) } returns listOf()
        every { polygonUtils.isPositionInPolygon(any(), any(), any()) } returns true

        assertThrows<WebClientResponseException> { classUnderTest.findPolygonByVin(vin) }
    }


    @Test
    fun `Given polygon geojson, When polygon is requested by invalid VIN, Then throws exception`() {
        val vin = "WAUSH54F08AASABCD"

        every { vehicleClient.getVehicles() } returns VehicleFaker.fakeVehicleResponseList()
        every { polygonClient.getPolygon() } returns LocationFaker.fakePolygonResponse()
        every { polygonUtils.getPolygonCoordinates(any()) } returns listOf()
        every { polygonUtils.isPositionInPolygon(any(), any(), any()) } returns true

        assertThrows<VehicleNotFoundException> { classUnderTest.findPolygonByVin(vin) }
    }

    @Test
    fun `Given polygon geojson, When polygon does not contain the valid VIN, Then throws exception`() {
        val vin = "WAUSH54F08AAS7GT5"

        every { vehicleClient.getVehicles() } returns VehicleFaker.fakeVehicleResponseList()
        every { polygonClient.getPolygon() } returns LocationFaker.fakePolygonResponse()
        every { polygonUtils.getPolygonCoordinates(any()) } returns listOf()
        every { polygonUtils.isPositionInPolygon(any(), any(), any()) } returns false

        assertThrows<PolygonNotFoundException> { classUnderTest.findPolygonByVin(vin) }
    }
}