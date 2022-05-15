package com.example.mobility.location.datasource.model

import com.fasterxml.jackson.annotation.JsonProperty

data class PolygonResponse(
    @JsonProperty(value = "_id")
    val id: String,
    val updatedAt: String,
    val createdAt: String,
    @JsonProperty(value = "__v")
    val v: String,
    val name: String,
    val cityId: String,
    val legacyId: String,
    val type: String,
    val geoFeatures: List<GeoFeature>,
    val options: Option,
    val timedOptions: List<TimedOption>,
    val geometry: GeometryPolygon,
    val version: String,
    @JsonProperty(value = "\$computed")
    val computed: Computed
)
