package com.example.feature.datasource.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Feature(
    val properties: Properties? = null,
)
