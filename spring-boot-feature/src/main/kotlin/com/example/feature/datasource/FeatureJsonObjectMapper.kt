package com.example.feature.datasource

import com.example.feature.configuration.ConfigProperties
import com.example.feature.datasource.model.FeatureCollection
import com.example.feature.exception.JsonFileException
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component

@Component
class FeatureJsonObjectMapper(private val configProperties: ConfigProperties) {

    fun parseGeoJson(): List<FeatureCollection> {
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
