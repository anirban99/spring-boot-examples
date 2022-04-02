package com.example.feature.datasource.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Acquisition(
    val beginViewingDate: Long?,
    val endViewingDate: Long?,
    val missionName: String?
)
