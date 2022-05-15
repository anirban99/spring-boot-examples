package com.example.mobility.vehicle.datasource

import com.example.mobility.vehicle.configuration.Car2GoApiProperties
import com.example.mobility.vehicle.datasource.model.VehicleResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException

@Component
class VehicleClient(
    private val car2GoApiProperties: Car2GoApiProperties
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    private var vehicleList: List<VehicleResponse> = arrayListOf()

    /**
     * Scheduler to fetch the vehicle list from car2go API after every 10 seconds
     */
    @Scheduled(fixedDelayString = "100000") //make this in the application yaml
    fun getVehiclesByLocation() {
        try {
            vehicleList = WebClient
                .create()
                .get()
                .uri(car2GoApiProperties.baseUrl + car2GoApiProperties.vehicle + car2GoApiProperties.locationName)
                .retrieve()
                .bodyToFlux(VehicleResponse::class.java)
                .collectList().block() as List<VehicleResponse>

            logger.info("Car2go Vehicle API is connected and converted to Object")
        } catch (e: WebClientResponseException) {
            logger.error("Car2go Vehicle API cannot establish connection: ", e)
        } catch (e: Exception) {
            logger.error("Car2go Vehicle API cannot be converted to Object: ", e)
        }

    }

    /**
     * Returns the list of vehicles
     */
    fun getVehicles(): List<VehicleResponse> {
        return vehicleList
    }
}