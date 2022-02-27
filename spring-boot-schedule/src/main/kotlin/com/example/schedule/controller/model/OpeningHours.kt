package com.example.schedule.controller.model

import com.example.schedule.utils.Type

data class OpeningHours(
    val type: Type,
    val value: Long
)