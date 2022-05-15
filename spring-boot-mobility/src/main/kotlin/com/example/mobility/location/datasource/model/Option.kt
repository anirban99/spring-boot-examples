package com.example.mobility.location.datasource.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class Option(
    val active: Boolean,
    @JsonProperty(value = "is_excluded")
    val isExcluded: Boolean,
    val area: BigDecimal
)
