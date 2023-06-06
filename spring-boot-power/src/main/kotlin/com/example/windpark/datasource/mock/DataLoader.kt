package com.example.windpark.datasource.mock

import com.example.windpark.configuration.ConfigProperties
import com.example.windpark.datasource.database.model.WindPark
import com.example.windpark.datasource.database.repository.WindParkRepository
import com.example.windpark.exception.JsonFileException
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.io.IOException

@Component
class DataLoader(
    private val configProperties: ConfigProperties,
    private val windParkRepository: WindParkRepository
    ) {

    private val log = LoggerFactory.getLogger(DataLoader::class.java)

    @EventListener(ApplicationReadyEvent::class)
    @Transactional
    fun loadListOfObjects() {
        try {
            log.info("Deleting database to import test data")
            windParkRepository.deleteAll()
            log.info("Deleting database finished!")

            log.info("Loading data from JSON file")
            val windParkEntities: List<WindPark> = jsonObjectMapper()
            windParkRepository.saveAll(windParkEntities)
            log.info("Done loading test data, wind parks saved!")
        } catch (e: IOException) {
            log.error("Unable to save Wind Parks: " + e.message)
        }
    }

    fun jsonObjectMapper(): List<WindPark> {
        return try {
            ClassPathResource(configProperties.fileName).apply {
                check(exists())
            }.inputStream.let {
                jacksonObjectMapper().readValue(it)
            }
        } catch (exception: IllegalStateException) {
            throw JsonFileException("The ${configProperties.fileName} file does not exist")
        } catch (exception: UnrecognizedPropertyException) {
            throw JsonFileException("Unrecognized field in Json, not marked as ignorable")
        } catch (exception: MismatchedInputException) {
            throw JsonFileException("The input file is invalid")
        } catch (exception: JsonParseException) {
            throw JsonFileException("The input file is invalid")
        }
    }
}