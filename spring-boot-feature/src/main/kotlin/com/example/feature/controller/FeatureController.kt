package com.example.feature.controller

import com.example.feature.controller.model.FeatureDto
import com.example.feature.controller.model.FeatureDto.ModelMapper.toFeatureDto
import com.example.feature.service.FeatureService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class FeatureController(private val featureService: FeatureService) {

    @GetMapping("/features")
    fun getFeatures(): List<FeatureDto> = featureService.findFeatures().map { it.toFeatureDto() }

    @GetMapping("/features/{featureId}/quicklook", produces = [MediaType.IMAGE_PNG_VALUE])
    fun getImageByFeatureId(@PathVariable("featureId") featureId: UUID): ByteArray =
        featureService.findImageByFeatureId(featureId)
}
