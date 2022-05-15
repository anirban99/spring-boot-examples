package com.example.mobility.vehicle.service

import com.example.mobility.location.datasource.PolygonClient
import com.example.mobility.location.datasource.model.PolygonResponse
import com.example.mobility.location.exception.PolygonNotFoundException
import com.example.mobility.location.utils.PolygonUtils
import com.example.mobility.vehicle.datasource.VehicleClient
import com.example.mobility.vehicle.datasource.model.VehicleResponse
import com.example.mobility.vehicle.exception.VehicleNotFoundException
import com.example.mobility.vehicle.service.model.PositionDetails
import com.example.mobility.vehicle.service.model.Vehicle
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class VehicleService(
    private val vehicleClient: VehicleClient,
    private val polygonClient: PolygonClient,
    private val polygonUtils: PolygonUtils
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    /**
     * Returns the vehicle list based on polygon Id
     */
    fun findVehicleByPolygonId(polygonId: String): List<Vehicle> {
        val polygon = findPolygonById(polygonClient.getPolygon(), polygonId)

        return findVehiclesInsidePolygon(vehicleClient.getVehicles(), polygon)
            .ifEmpty {
                logger.info("Vehicle is not found in the Polygon Id: $polygonId")
                throw VehicleNotFoundException(
                    HttpStatus.NOT_FOUND,
                    "Vehicle is not found in the Polygon Id: $polygonId"
                )
            }
    }

    /**
     * Finds the polygon from the polygon list based on the polygon Id
     */
    private fun findPolygonById(polygons: List<PolygonResponse>, polygonId: String): PolygonResponse {
        return polygons.find { it.id == polygonId }
            ?.also { logger.info("Polygon Id $polygonId is available in GeoJson") }
            ?: throw PolygonNotFoundException(HttpStatus.NOT_FOUND, "Polygon Id $polygonId is not available in GeoJson")
    }

    /**
     * Finds the list of vehicles inside the polygon
     */
    private fun findVehiclesInsidePolygon(
        vehiclesByLocation: List<VehicleResponse>,
        polygon: PolygonResponse
    ): List<Vehicle> {
        return vehiclesByLocation
            .filter { vehicle ->
                polygonUtils.isPositionInPolygon(
                    longitude = vehicle.position.longitude.toDouble(),
                    latitude = vehicle.position.latitude.toDouble(),
                    position = polygonUtils.getPolygonCoordinates(polygon).toTypedArray()
                )
            }.map {
                Vehicle(
                    polygonId = polygon.id,
                    id = it.id,
                    locationId = it.locationId,
                    vin = it.vin,
                    numberPlate = it.numberPlate,
                    position = PositionDetails(
                        longitude = it.position.longitude,
                        latitude = it.position.latitude
                    ),
                    fuel = it.fuel,
                    model = it.model
                )
            }
    }
}
