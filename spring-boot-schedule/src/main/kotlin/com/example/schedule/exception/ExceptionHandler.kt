package com.example.schedule.exception

import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    fun handleHttpMessageNotReadableException(exception: HttpMessageNotReadableException): ErrorMessage {
        return ErrorMessage(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = "Invalid Request Body"
        )
    }

    @ExceptionHandler(InvalidOpeningHoursException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    fun handleInvalidOpeningHoursException(exception: InvalidOpeningHoursException): ErrorMessage {
        return ErrorMessage(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = exception.message ?: "Invalid Opening Hours"
        )
    }

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    fun handleHttpMessageNotReadableException(exception: RuntimeException): ErrorMessage {
        return ErrorMessage(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = "Runtime Exception has occurred"
        )
    }
}