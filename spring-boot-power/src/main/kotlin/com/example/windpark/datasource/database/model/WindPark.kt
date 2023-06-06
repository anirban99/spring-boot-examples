package com.example.windpark.datasource.database.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.Entity
import javax.persistence.Id

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
data class WindPark(
    @Id
    val id: String,
    val windPark: String,
    val powerProduced: Double,
    val period: String,
    val timestamp: String,
    val createdOn: String,
    val updatedOn: String
)
