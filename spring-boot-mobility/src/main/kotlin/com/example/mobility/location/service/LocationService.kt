package com.example.mobility.location.service

import com.example.mobility.location.datasource.PolygonClient
import com.example.mobility.location.datasource.model.PolygonResponse
import com.example.mobility.location.exception.PolygonNotFoundException
import com.example.mobility.location.service.model.Location
import com.example.mobility.location.utils.PolygonUtils
import com.example.mobility.vehicle.datasource.VehicleClient
import com.example.mobility.vehicle.datasource.model.VehicleResponse
import com.example.mobility.vehicle.exception.VehicleNotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class LocationService(
    private val vehicleClient: VehicleClient,
    private val polygonClient: PolygonClient,
    private val polygonUtils: PolygonUtils
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    /**
     * Returns the polygon as part of the location resource based on VIN
     */
    fun findPolygonByVin(vin: String): Location {
        val vehicle = findVehicleByVin(vehicleClient.getVehicles(), vin)

        return findPolygonContainingVehicle(polygonClient.getPolygon(), vehicle)
    }

    /**
     * Finds the vehicle from the vehicle client list based on VIN
     */
    private fun findVehicleByVin(vehiclesByLocation: List<VehicleResponse>, vin: String): VehicleResponse {
        return vehiclesByLocation.find { it.vin == vin }?.also { logger.info("VIN: $vin is available in Car2Go API for the provided location") }
            ?: throw VehicleNotFoundException(HttpStatus.NOT_FOUND, "VIN: $vin is not available in Car2Go API for the provided location")
    }

    /**
     * Finds the polygon from the polygon list containing the vehicle
     */
    private fun findPolygonContainingVehicle(polygons: List<PolygonResponse>, vehicle: VehicleResponse): Location {
        return polygons.find { polygon ->
            polygonUtils.isPositionInPolygon(
                longitude = vehicle.position.longitude.toDouble(),
                latitude = vehicle.position.latitude.toDouble(),
                position = polygonUtils.getPolygonCoordinates(polygon).toTypedArray()
            )
        }?.let { Location(polygonId = it.id, vin = vehicle.vin) }
            ?: throw PolygonNotFoundException(HttpStatus.NOT_FOUND, "No Polygon is found containing the VIN: ${vehicle.vin}")
    }
}