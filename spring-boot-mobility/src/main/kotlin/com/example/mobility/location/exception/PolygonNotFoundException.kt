package com.example.mobility.location.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class PolygonNotFoundException(status: HttpStatus, override val message: String) :
    ResponseStatusException(status, message)