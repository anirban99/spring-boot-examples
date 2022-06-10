package com.example.weather.datasource.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class TemperatureJsonResponse (val main: Main)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Main(val temp: Double)
