package com.example.mobility.location.datasource.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ActiveTimedOption(
    val min: Int,
    val max: Int,
    @JsonProperty(value = "idle_time")
    val idleTime: Int,
    val revenue: Int,
    @JsonProperty(value = "walking_range1")
    val walkingRange1: Int,
    @JsonProperty(value = "walking_range2")
    val walkingRange2: Int
)
