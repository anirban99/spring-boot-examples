package com.example.api.integration

import com.example.api.service.EmployeeService
import com.example.api.utils.faker.EmployeeFaker
import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.doReturn
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ActiveProfiles(value= ["test"])
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeWebApplicationTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var employeeService: EmployeeService

    private val id = Math.random().toLong()

    @Test
    fun `Given valid url, when GET employee list is called, then returns 200 `() {
        val employeeList = listOf(
                EmployeeFaker.fakeEmployee(),
                EmployeeFaker.fakeEmployee(),
                EmployeeFaker.fakeEmployee(),
                EmployeeFaker.fakeEmployee()
        )
        Mockito.`when`(employeeService.getAllEmployees()).doReturn(employeeList)

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty)
    }

    @Test
    fun `Given valid url, when GET employee by id is called, then returns 200 `() {
        val employee = EmployeeFaker.fakeEmployee().copy(id = id)

        Mockito.`when`(employeeService.getEmployeesById(id)).doReturn(employee)

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
    }

    @Test
    fun `Given valid url, when POST employee is called, then returns 200 `() {
        val employee = EmployeeFaker.fakeEmployee().copy(id = id)

        Mockito.`when`(employeeService.createEmployee(employee)).doReturn(employee)

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees")
                .content(objectMapper.writeValueAsString(employee))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(employee.firstName))
    }

    @Test
    fun `Given valid url, when PUT employee is called, then returns 200 `() {
        val employee = EmployeeFaker.fakeUpdatedEmployee().copy(id = id)

        Mockito.`when`(employeeService.updateEmployeeById(id, employee)).doReturn(employee)

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/employees/{id}", id)
                .content(objectMapper.writeValueAsString(employee))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(employee.firstName))
                .andExpect(MockMvcResultMatchers.jsonPath("$.emailId").value(employee.emailId))
    }

    @Test
    fun `Given valid url, when DELETE employee by id is called, then returns 200 `() {
        val employee = EmployeeFaker.fakeEmployee().copy(id = id)

        Mockito.`when`(employeeService.deleteEmployeesById(id)).doReturn(employee)

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/employees/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
    }
}