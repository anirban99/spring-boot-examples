package com.example.schedule.exception

class ErrorMessage (
    /**
     * Http status code
     */
    val status: Int,

    /**
     * A human-readable error name
     */
    val error: String,

    /**
     * A human-readable error message
     */
    val message: String
)