package com.example.inventory.datasource.mock

import com.example.inventory.datasource.database.model.CarEntity
import com.example.inventory.datasource.database.repository.CarRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Profile
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * This class is used in development environments and is used to load test data from CSV files into the database.
 */

@Service
//@Profile("test-data")
@Suppress("LongParameterList")
class DataLoader(
    //Sources
    private val carInventoryFileSource: CsvFileSource,
    //Repository
    private val carRepository: CarRepository
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    /**
     * Loads all csv data in to mock store.
     * It will check where possible for already loaded data.
     * The transaction data from the rdh will always be deleted and reloaded.
     *
     * ApplicationReadyEvent will be triggered after the whole application was started
     */
    @EventListener(ApplicationReadyEvent::class)
    @Transactional
    fun load() {
        logger.info("Deleting database to import test data...")

        // Tables must be deleted in correct order to not conflict with foreign key constraints
        carRepository.deleteAll()
        logger.info("Deleting database finished!")

        logger.info("Loading data from CSVs...")
        val cars = loadCarFromCsv()
        carRepository.saveAll(cars)
        logger.info("Imported cars: ${cars.joinToString()}")

        logger.info("Done loading test data !!")
    }

    private fun loadCarFromCsv(): List<CarEntity> {
        logger.info("Loading Cars from Inventory CSV file...")
        return InventoryCsvToEntityMapper.mapToCarEntity(
            carInventoryFileSource.loadListOfObjects(HashMap<String, String>().javaClass)
        )
    }
}