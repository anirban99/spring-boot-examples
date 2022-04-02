package com.example.feature.datasource.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class FeatureCollection(val features: List<Feature>)
