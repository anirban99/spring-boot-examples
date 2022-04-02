package com.example.feature.datasource.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.UUID

@JsonIgnoreProperties(ignoreUnknown = true)
data class Properties(
    val id: UUID,
    val timestamp: Long,
    val acquisition: Acquisition? = null,
    val quicklook: String?
)
