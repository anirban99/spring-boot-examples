package com.example.mobility.utils.faker

import com.example.mobility.location.datasource.model.PolygonResponse
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource

class LocationFaker {
    companion object{

        fun fakePolygonResponse(): List<PolygonResponse> {
            val inputStream = ClassPathResource("polygon-test.json").inputStream
            val mapper = jacksonObjectMapper()
            return mapper.readValue(inputStream)
        }
    }
}