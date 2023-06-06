package com.example.windpark.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleIllegalArgumentException(exception: IllegalArgumentException): ErrorMessage {
        return ErrorMessage(
            timestamp = Date(),
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = exception.message
        )
    }

    @ExceptionHandler(JsonFileException::class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleJsonFileException(exception: JsonFileException): ErrorMessage {
        return ErrorMessage(
            timestamp = Date(),
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = exception.message
        )
    }

    @ExceptionHandler(WindParkNotFoundException::class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    fun handleWindParkNotFoundException(exception: WindParkNotFoundException): ErrorMessage {
        return ErrorMessage(
            timestamp = Date(),
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message
        )
    }

    @ExceptionHandler(WindParkUpdateFailedException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    fun handleWindParkUpdateFailedException(exception: WindParkUpdateFailedException): ErrorMessage {
        return ErrorMessage(
            timestamp = Date(),
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = exception.message
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleGlobalException(exception: Exception): ErrorMessage {
        return ErrorMessage(
            timestamp = Date(),
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = exception.message
        )
    }

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleRuntimeException(exception: RuntimeException): ErrorMessage {
        return ErrorMessage(
            timestamp = Date(),
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = exception.message
        )
    }
}