package com.example.api.service

import com.example.api.exception.EmployeeNotFoundException
import com.example.api.repository.EmployeeRepository
import com.example.api.utils.faker.EmployeeFaker
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import java.util.*

class EmployeeServiceTest {

    private val employeeRepository: EmployeeRepository = mock { }
    private val classUnderTest = EmployeeService(employeeRepository)
    private val id = 11.toLong()

    @Test
    fun `given employees, when list of employees are requested, then all employees are returned`() {
        val employeeList = listOf(
                EmployeeFaker.fakeEmployeeEntity(),
                EmployeeFaker.fakeEmployeeEntity(),
                EmployeeFaker.fakeEmployeeEntity(),
                EmployeeFaker.fakeEmployeeEntity()
        )

        `when`(employeeRepository.findAll()).doReturn(employeeList)
        assertEquals(4, classUnderTest.getAllEmployees().size)
    }

    @Test
    fun `given employees, when employee is requested by id, then one employee is returned`() {
        `when`(employeeRepository.findById(id)).doReturn(
                Optional.of(EmployeeFaker.fakeEmployeeEntity().copy(id = id))
        )
        val result = classUnderTest.getEmployeesById(id)
        assertEquals(id, result.id)
    }

    @Test
    fun `given employees, when employee is requested by invalid id, throws exception`() {
        val invalidId = Math.random().toLong()

        assertThrows<EmployeeNotFoundException> { classUnderTest.getEmployeesById(invalidId) }
    }

    @Test
    fun `given new employee details, when employee is created, then returns the new employee details`() {
        `when`(employeeRepository.save(EmployeeFaker.fakeEmployeeEntity())).doReturn(
                EmployeeFaker.fakeEmployeeEntity()
        )

        val result = classUnderTest.createEmployee(EmployeeFaker.fakeEmployee())
        assertEquals("Lesnar", result.lastName)
        assertEquals("Brock", result.firstName)
    }

    @Test
    fun `given updated employee details, when employee is updated by valid id, then it returns the updated employee details`() {
        `when`(employeeRepository.findById(id)).doReturn(
                Optional.of(EmployeeFaker.fakeEmployeeEntity().copy(id = id))
        )
        `when`(employeeRepository.save(EmployeeFaker.fakeUpdatedEmployeeEntity().copy(id = id))).doReturn(
                EmployeeFaker.fakeUpdatedEmployeeEntity().copy(id = id)
        )

        val result = classUnderTest.updateEmployeeById(id, EmployeeFaker.fakeUpdatedEmployee().copy(id = id))
        assertEquals("Lesnar", result.lastName)
        assertEquals("Shawn", result.firstName)
    }

    @Test
    fun `given employees, when employee is deleted by id, then it returns the deleted employee details`() {
        `when`(employeeRepository.findById(id)).doReturn(
                Optional.of(EmployeeFaker.fakeEmployeeEntity().copy(id = id))
        )
        doNothing().`when`(employeeRepository).delete(EmployeeFaker.fakeEmployeeEntity().copy(id = id))

        val result = classUnderTest.deleteEmployeesById(id)
        assertEquals(id, result.id)
    }
}
