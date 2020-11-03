package com.example.api.integration

import com.example.api.service.EmployeeService
import com.example.api.utils.faker.EmployeeFaker
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

@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeWebApplicationTest {
    @Autowired
    private lateinit var mockMvc: MockMvc
//
//    @Autowired
//    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var employeeService: EmployeeService

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
}