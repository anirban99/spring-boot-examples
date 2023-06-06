package com.example.windpark.service

import com.example.windpark.datasource.database.repository.WindParkRepository
import com.example.windpark.datasource.database.model.WindPark
import com.example.windpark.exception.WindParkNotFoundException
import com.example.windpark.exception.WindParkUpdateFailedException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Service
class WindParkService(private val windParkRepository: WindParkRepository) {
    private val log = LoggerFactory.getLogger(WindParkService::class.java)

    fun getAllWindParks(): List<WindPark> = windParkRepository.findAll().ifEmpty {
        throw WindParkNotFoundException("The wind park list is empty")
    }

    fun getWindParkById(windParkId: String): WindPark = windParkRepository.findById(windParkId).orElseThrow {
        WindParkNotFoundException("The wind park with id = $windParkId does not exist")
    }

    fun updatePowerProducedByWindParkId(windParkId: String, key: String, value: String): WindPark {
        val windPark = getWindParkById(windParkId)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:00'Z'")

        return if (key.equals("powerProduced", ignoreCase = true)) {
            saveWindPowerPark(
                WindPark(
                    id = windPark.id,
                    windPark = windPark.windPark,
                    powerProduced = value.toDouble(),
                    period = windPark.period,
                    timestamp = windPark.timestamp,
                    createdOn = windPark.createdOn,
                    updatedOn = LocalDateTime.now(ZoneId.of("UTC")).format(formatter)
                )
            )
        } else throw WindParkUpdateFailedException("Invalid Request")
    }

    fun saveWindPowerPark(windPark: WindPark): WindPark = windParkRepository.save(windPark)

    fun deleteWindParkById(windParkId: String) {
        return if (windParkRepository.existsById(windParkId)) {
            windParkRepository.deleteById(windParkId)
        } else throw WindParkNotFoundException("The wind park with id = $windParkId does not exist")
    }
}