package com.example.feature.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.Date

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

    @ExceptionHandler(FeatureNotFoundException::class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    fun handleFeatureNotFoundException(exception: FeatureNotFoundException): ErrorMessage {
        return ErrorMessage(
            timestamp = Date(),
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message
        )
    }

    @ExceptionHandler(ImageNotFoundException::class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    fun handleImageNotFoundException(exception: ImageNotFoundException): ErrorMessage {
        return ErrorMessage(
            timestamp = Date(),
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
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
