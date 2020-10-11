package com.example.api.controller

import com.example.api.service.EmployeeService
import com.example.api.utils.faker.EmployeeFaker
import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.doReturn
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest(controllers = [EmployeeController::class])
@AutoConfigureMockMvc(addFilters = false)  //https://stackoverflow.com/questions/47593537/disable-spring-security-config-class-for-webmvctest-in-spring-boot
class EmployeeServiceWebMvcTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var employeeService: EmployeeService

    private val id = Math.random().toLong()

//    private val employeeService: EmployeeService = mock {  }

    @Test
    fun `Given valid url, when GET employee list is called, then returns 200 `() {
        val employeeList = listOf(
                EmployeeFaker.fakeEmployee(),
                EmployeeFaker.fakeEmployee(),
                EmployeeFaker.fakeEmployee(),
                EmployeeFaker.fakeEmployee()
        )
        Mockito.`when`(employeeService.getAllEmployees()).doReturn(employeeList)

        mockMvc.perform(get("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.[*].id").isNotEmpty)
    }

    @Test
    fun `Given valid url, when GET employee by id is called, then returns 200 `() {
        val employee = EmployeeFaker.fakeEmployee().copy(id = id)

        Mockito.`when`(employeeService.getEmployeesById(id)).doReturn(employee)

        mockMvc.perform(get("/api/v1/employees/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.id").value(id))
    }

    @Test
    fun `Given valid url, when POST employee is called, then returns 200 `() {
        val employee = EmployeeFaker.fakeEmployee().copy(id = id)

        Mockito.`when`(employeeService.createEmployee(employee)).doReturn(employee)

        mockMvc.perform(post("/api/v1/employees")
                .content(objectMapper.writeValueAsString(employee))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.firstName").value("Brock"))
    }
}