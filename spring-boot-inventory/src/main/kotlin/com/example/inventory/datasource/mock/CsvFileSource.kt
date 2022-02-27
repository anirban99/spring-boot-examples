package com.example.inventory.datasource.mock

import com.fasterxml.jackson.dataformat.csv.CsvMapper
import org.aspectj.apache.bcel.util.ClassPath
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.io.ClassPathResource
import java.io.File
import java.util.*

/**
 * CsvFileSource represents a single CSV file - https://www.baeldung.com/spring-app-setup-with-csv-files
 */
class CsvFileSource(private val fileName: String) {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    /**
     * Reads all objects from the csv file
     */
    fun <T> loadListOfObjects(type: Class<T>): List<T> {
        logger.info("Load list of objects to $fileName")
        return try {
            val mapper = CsvMapper()
            val schema = mapper.schemaWithHeader()
            val csvFile = ClassPathResource(fileName).inputStream
            mapper.readerFor(type)
                .with(schema)
                .readValues<T>(csvFile)
                .readAll()
        } catch (e: Exception) {
            logger.error("Error occurred while loading object list from file $fileName", e);
            Collections.emptyList();
        }
    }
}