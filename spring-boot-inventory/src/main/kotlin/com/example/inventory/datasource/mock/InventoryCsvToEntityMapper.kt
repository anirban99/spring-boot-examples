package com.example.inventory.datasource.mock

import com.example.inventory.datasource.database.model.CarEntity
import com.example.inventory.utils.Condition
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder

object InventoryCsvToEntityMapper {
    /**
     * This function converts a list of key-value pairs loaded from a csv file
     * to a list of CarEntity.
     */
    fun mapToCarEntity(csvBalances: List<HashMap<String, String>>): List<CarEntity> {
        val format = DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-d"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-dd"))
            .toFormatter()

        return csvBalances.map {
            CarEntity(
                id = it["ID"]!!.toInt(),
                make = it["Make"]!!,
                model = it["Model"]!!,
                price = it["Price"]!!.toDouble(),
                condition = it["Condition"]?.ifEmpty { null }
                    ?.let { value -> Condition.from(value) },
                discountPrice = it["DiscountPrice"]?.ifEmpty { null },
                date = it["Date"]?.ifEmpty { null }
                    ?.let { value -> LocalDate.parse(value, format) }
            )
        }
    }
}