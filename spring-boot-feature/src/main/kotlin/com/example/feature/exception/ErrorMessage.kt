package com.example.feature.exception

import java.util.Date

class ErrorMessage(
    val timestamp: Date,
    val status: Int,
    val error: String,
    val message: String?
)
