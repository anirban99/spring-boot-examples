package com.example.mobility.location.datasource.model

data class TimedOption(
    val key: String,
    val changesOverTime: List<List<Int>>
)
