package com.example.api.integration

import com.example.api.controller.EmployeeController
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.assertj.core.api.Assertions.assertThat

@ActiveProfiles("test")
@SpringBootTest
class SmokeTest {

    @Autowired
    private lateinit var employeeController: EmployeeController

    @Test
    @Throws(Exception::class)
    fun contextLoads() {
        assertThat(employeeController).isNotNull()
    }
}
