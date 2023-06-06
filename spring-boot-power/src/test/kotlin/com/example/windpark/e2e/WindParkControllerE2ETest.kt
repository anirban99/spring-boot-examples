package com.example.windpark.e2e

import com.example.windpark.WindParkApplication
import com.example.windpark.controller.model.PowerProducedDto
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(value= ["test"])
@SpringBootTest(
    classes = [WindParkApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WindParkControllerE2ETest {

    @LocalServerPort
    var port: Int = 0

    @BeforeAll
    fun beforeAll() {
        RestAssured.port = port
    }

    @Nested
    inner class GetRequestMapping{
        @Test
        fun `Given wind parks collection, When GET request for all wind parks is requested, Then should return should return HTTP status 200`() {

            given()
                .get("/wind-parks")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
        }

        @Test
        fun `Given wind parks collection, When GET request for wind park by id is requested, Then should return should return HTTP status 200`() {
            val windParkId = "aff8c6b2-3919-4847-b41d-9c4182832841"
            given()
                .get("/wind-parks/$windParkId")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("id", equalTo(windParkId))
        }
    }

    @Nested
    inner class PatchRequestMapping{
        @Test
        fun`Given wind parks collection, When PATCH request for updating power produced by wind park id is requested, Then should return should return HTTP status 200`() {
            val windParkId = "aff8c6b2-3919-4847-b41d-9c4182832841"
            val updatedPowerProduced = "9.75"

            val powerProduced: Double =
                given()
                .contentType(ContentType.JSON)
                .body(PowerProducedDto(operation="update",key="powerProduced",value=updatedPowerProduced))
                .patch("/wind-parks/$windParkId")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .extract()
                .path("powerProduced")

            assertThat(powerProduced).isEqualTo(updatedPowerProduced.toDouble())
        }

        @Test
        fun `Given wind parks collection, When PATCH request for updating power produced by wind park id is requested with invalid operation, Then should return HTTP status 400`() {
            val windParkId = "aff8c6b2-3919-4847-b41d-9c4182832841"
            val updatedPowerProduced = "9.75"

            given()
                .contentType(ContentType.JSON)
                .body(PowerProducedDto(operation="insert",key="powerProduced",value=updatedPowerProduced))
                .patch("/wind-parks/$windParkId")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
        }
    }

    @Nested
    inner class DeleteRequestMapping{
        @Test
        fun `Given wind parks collection, When DELETE request for wind park by id is requested, Then should return HTTP status 200`() {
            val windParkId = "aff8c6b2-3919-4847-b41d-9c4182832841"
            given()
                .delete("/wind-parks/$windParkId")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
        }

        @Test
        fun `Given wind parks collection, When DELETE request wind park by id is requested which is not in collection, Then should return HTTP status 404`() {
            val windParkId = "0008c6b2-3919-4847-b41d-9c4182832841"
            given()
                .delete("/wind-parks/$windParkId")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
        }
    }
}