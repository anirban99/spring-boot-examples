package com.example.windpark.exception

import java.util.*

class ErrorMessage(
    val timestamp: Date,
    val status: Int,
    val error: String,
    val message: String?
)