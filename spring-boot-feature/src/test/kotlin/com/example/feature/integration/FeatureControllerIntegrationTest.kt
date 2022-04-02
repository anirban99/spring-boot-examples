package com.example.feature.integration

import com.example.feature.util.FeatureFaker
import io.restassured.RestAssured
import io.restassured.parsing.Parser
import io.restassured.RestAssured.given
import java.util.UUID
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FeatureControllerIntegrationTest {
    @LocalServerPort
    var port: Int = 0

    @BeforeAll
    fun beforeAll() {
        RestAssured.port = port
    }

    @Nested
    inner class GetFeatures {
        @Test
        fun `Given a feature collection, When GET features is called, Then should return all features`() {
            val expectedFeatures = FeatureFaker.fakeFeatureDto()

            given()
                .get("/features")
                .then()
                .statusCode(200)
                .also { validatableResponse ->
                    expectedFeatures.forEach { feature ->
                        validatableResponse.body("id[${feature.key}]", equalTo(feature.value.id.toString()))
                            .body("timestamp[${feature.key}]", equalTo(feature.value.timestamp))
                            .body("beginViewingDate[${feature.key}]", equalTo(feature.value.beginViewingDate))
                            .body("endViewingDate[${feature.key}]", equalTo(feature.value.endViewingDate))
                            .body("missionName[${feature.key}]", equalTo(feature.value.missionName))
                    }
                }
        }
    }

    @Nested
    inner class GetImageByFeatureId {
        @Test
        fun `Given a feature collection, When GET image by valid feature Id, Then should return the image`() {
            val featureId = UUID.fromString("39c2f29e-c0f8-4a39-a98b-deed547d6aea")
            RestAssured.registerParser("image/png", Parser.JSON)

            given()
                .get("/features/$featureId/quicklook")
                .then()
                .statusCode(200)
                .assertThat()
                .contentType(MediaType.IMAGE_PNG_VALUE)
        }

        @Test
        fun `Given a feature collection, When GET image by invalid feature Id, Then should throw ImageNotFoundException`() {
            val featureId = UUID.fromString("40c2f29e-c0f8-4a39-a98b-deed547d6aea")
            RestAssured.registerParser("image/png", Parser.JSON)

            given()
                .get("/features/$featureId/quicklook")
                .then()
                .statusCode(404)
                .body("message", equalTo("Image is not found for the feature id: $featureId"))
        }

        @Test
        fun `Given a feature collection with null quicklook, When GET image by valid feature Id, Then should throw ImageNotFoundException`() {
            val featureId = UUID.fromString("b0d3bf6a-ff54-49e0-a4cb-e57dcb68d3b5")
            RestAssured.registerParser("image/png", Parser.JSON)

            given()
                .get("/features/$featureId/quicklook")
                .then()
                .statusCode(404)
                .body("message", equalTo("Image is not found for the feature id: $featureId"))
        }
    }
}
