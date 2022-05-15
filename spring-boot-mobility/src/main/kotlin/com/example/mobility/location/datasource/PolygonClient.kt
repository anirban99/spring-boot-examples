package com.example.mobility.location.datasource

import com.example.mobility.location.datasource.model.PolygonResponse
import com.example.mobility.location.exception.PolygonNotFoundException
import com.example.mobility.location.utils.Constants.Companion.POLYGON
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class PolygonClient {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    /**
     * Reads the polygon Gson and converts it to an object
     */
    fun getPolygon(): List<PolygonResponse> {
        try {
            val inputStream = ClassPathResource(POLYGON).inputStream
            val mapper = jacksonObjectMapper()
            logger.info("Converting GeoJson to Object")
            return mapper.readValue(inputStream)
        } catch (e: Exception) {
            logger.error("Error while converting GeoJson to Object : ", e)
            throw PolygonNotFoundException(HttpStatus.NOT_FOUND, "Error while converting GeoJson to Object")
        }
    }
}