package com.example.mobility.vehicle.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class VehicleNotFoundException(status: HttpStatus, override val message: String) :
    ResponseStatusException(status, message)