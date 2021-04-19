package com.example.api.integration

import com.example.api.controller.model.EmployeeDto
import com.example.api.service.EmployeeService
import com.example.api.utils.faker.EmployeeFaker
import com.nhaarman.mockitokotlin2.doReturn
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpMethod
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import java.net.URI

/**
 * TODO: Add tests for POST, PUT and DELETE controllers
 */
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeHttpRequestTest {
    @LocalServerPort
    private val applicationPort: Int = 8082

    @Autowired
    private lateinit var testRestTemplate: TestRestTemplate

    @MockBean
    private lateinit var employeeService: EmployeeService

    private val id = Math.random().toLong()

    private fun applicationUrl() = "http://localhost:$applicationPort"

    @Test
    fun `Given valid url, when GET employee is called, then returns employee list with status 200`() {
        val employeeList = listOf(
                EmployeeFaker.fakeEmployee(),
                EmployeeFaker.fakeEmployee(),
                EmployeeFaker.fakeEmployee(),
                EmployeeFaker.fakeEmployee()
        )
        Mockito.`when`(employeeService.getAllEmployees()).doReturn(employeeList)

        val response = testRestTemplate.withBasicAuth("user", "user")
                .exchange(
                        URI(applicationUrl() + "/employees"),
                        HttpMethod.GET,
                        HttpEntity(""),
                        List::class.java)

        assertNotNull(response)
        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    fun `Given valid url with invalid login credentials, when GET employee is called, then returns status 401`() {
        val employeeList = listOf(
                EmployeeFaker.fakeEmployee(),
                EmployeeFaker.fakeEmployee(),
                EmployeeFaker.fakeEmployee(),
                EmployeeFaker.fakeEmployee()
        )
        Mockito.`when`(employeeService.getAllEmployees()).doReturn(employeeList)

        val response = testRestTemplate.withBasicAuth("user", "abc")
                .exchange(
                        URI(applicationUrl() + "/employees"),
                        HttpMethod.GET,
                        HttpEntity(""),
                        Void::class.java)

        assertEquals(HttpStatus.UNAUTHORIZED, response.statusCode)
    }

    @Test
    fun `Given valid url, when GET employee by id is called, then returns employee with status 200 `() {
        val employee = EmployeeFaker.fakeEmployee().copy(id = id)

        Mockito.`when`(employeeService.getEmployeesById(id)).doReturn(employee)

        val response = testRestTemplate.withBasicAuth("user", "user")
                .getForEntity(applicationUrl() + "/employees/{id}", EmployeeDto::class.java, id)

        assertNotNull(response)
        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    fun `Given valid url with invalid login credentials, when GET employee by id is called, then returns status 401 `() {
        val employee = EmployeeFaker.fakeEmployee().copy(id = id)

        Mockito.`when`(employeeService.getEmployeesById(id)).doReturn(employee)

        val response = testRestTemplate.withBasicAuth("xyz", "user")
                .getForEntity(applicationUrl() + "/employees/{id}", Void::class.java, id)

        assertEquals(HttpStatus.UNAUTHORIZED, response.statusCode)
    }
}
