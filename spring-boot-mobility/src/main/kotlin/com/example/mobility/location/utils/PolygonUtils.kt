package com.example.mobility.location.utils

import com.example.mobility.location.datasource.model.PolygonResponse
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.LinearRing
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import kotlin.streams.toList

@Component
class PolygonUtils {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    /**
     * Returns the list of polygon coordinates
     */
    fun getPolygonCoordinates(polygon: PolygonResponse): List<Coordinate> {
        return polygon.geometry.coordinates[0].stream().map { Coordinate(it[0].toDouble(), it[1].toDouble()) }.toList()
    }

    /**
     * Verifies whether the given vehicle position lies inside a polygon
     */
    fun isPositionInPolygon(longitude: Double, latitude: Double, position: Array<Coordinate>): Boolean {
        try {
            val geometryFactory = GeometryFactory()
            val linearRing: LinearRing = geometryFactory.createLinearRing(position)
            val polygon = geometryFactory.createPolygon(linearRing, null)
            val coordinate = Coordinate(longitude, latitude)
            val point = geometryFactory.createPoint(coordinate)
            return polygon.contains(point)
        } catch (e: Exception) {
            logger.error("Exception while checking position coordinates inside polygon", e)
        }
        return false
    }
}