package com.example.mobility.vehicle.service

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

class VehicleServiceUnitTest {

    private val vehicleClient: VehicleClient = mockk()
    private val polygonClient: PolygonClient = mockk()
    private val polygonUtils: PolygonUtils = mockk()
    private val classUnderTest = VehicleService(vehicleClient, polygonClient, polygonUtils)

    @Test
    fun `Given vehicle list, When vehicle is requested by valid polygon Id, Then returns list of vehicles`() {
        val polygonId = "58a58bf685979b5415f3a39a"
        val vehicleFakerList = VehicleFaker.fakeVehicleResponseList()

        every { vehicleClient.getVehicles() } returns vehicleFakerList
        every { polygonClient.getPolygon() } returns LocationFaker.fakePolygonResponse()
        every { polygonUtils.getPolygonCoordinates(any()) } returns listOf()
        every { polygonUtils.isPositionInPolygon(any(), any(), any()) } returns true

        val result = classUnderTest.findVehicleByPolygonId(polygonId)

        assertEquals(polygonId, result[0].polygonId)
        assertEquals(vehicleFakerList.size, result.size)
    }

    @Test
    fun `Given vehicle list, When vehicle is requested by valid polygon Id but Polygon client has exception, Then throws exception`() {
        val polygonId = "58a58bf685979b5415f3a39a"

        every { vehicleClient.getVehicles() } returns VehicleFaker.fakeVehicleResponseList()
        every { polygonClient.getPolygon() } throws PolygonNotFoundException(
            HttpStatus.NOT_FOUND,
            "Error while converting GeoJson to Object"
        )
        every { polygonUtils.getPolygonCoordinates(any()) } returns listOf()
        every { polygonUtils.isPositionInPolygon(any(), any(), any()) } returns true

        assertThrows<PolygonNotFoundException> { classUnderTest.findVehicleByPolygonId(polygonId) }
    }

    @Test
    fun `Given vehicle list, When vehicle is requested by valid polygon Id but Vehicle client has exception, Then throws exception`() {
        val polygonId = "58a58bf685979b5415f3a39a"

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

        assertThrows<WebClientResponseException> { classUnderTest.findVehicleByPolygonId(polygonId) }
    }

    @Test
    fun `Given vehicle list, When vehicle is requested by invalid polygon Id, Then throws exception`() {
        val polygonId = "58a58bf685979b5415f3a40b"

        every { vehicleClient.getVehicles() } returns VehicleFaker.fakeVehicleResponseList()
        every { polygonClient.getPolygon() } returns LocationFaker.fakePolygonResponse()
        every { polygonUtils.getPolygonCoordinates(any()) } returns listOf()
        every { polygonUtils.isPositionInPolygon(any(), any(), any()) } returns true

        assertThrows<PolygonNotFoundException> { classUnderTest.findVehicleByPolygonId(polygonId) }
    }

    @Test
    fun `Given vehicle list, When vehicle is not inside the valid polygon Id, Then throws exception`() {
        val polygonId = "58a58bf685979b5415f3a39a"

        every { vehicleClient.getVehicles() } returns VehicleFaker.fakeVehicleResponseList()
        every { polygonClient.getPolygon() } returns LocationFaker.fakePolygonResponse()
        every { polygonUtils.getPolygonCoordinates(any()) } returns listOf()
        every { polygonUtils.isPositionInPolygon(any(), any(), any()) } returns false

        assertThrows<VehicleNotFoundException> { classUnderTest.findVehicleByPolygonId(polygonId) }
    }
}