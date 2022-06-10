package com.example.weather.exception

class ClientResponseException(override val message: String?): Exception(message) {
}