package com.example.api.exception

import org.springframework.http.HttpStatus

class EmployeeNotFoundException(val statusCode: HttpStatus, val reason: String) : Exception()