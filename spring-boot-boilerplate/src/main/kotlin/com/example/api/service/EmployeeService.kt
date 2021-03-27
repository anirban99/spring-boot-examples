package com.example.api.service

import com.example.api.exception.EmployeeNotFoundException
import com.example.api.repository.EmployeeRepository
import com.example.api.repository.model.Employee
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

/**
 * Service for interactions with employee domain object
 */
@Service
class EmployeeService(private val employeeRepository: EmployeeRepository) {

    /**
     * Get all employees list.
     *
     * @return the list
     */
    fun getAllEmployees(): List<Employee> = employeeRepository.findAll()

    /**
     * Gets employees by id.
     *
     * @param employeeId the employee id
     * @return the employee by id
     * @throws EmployeeNotFoundException the employee not found exception
     */
    fun getEmployeesById(employeeId: Long): Employee = employeeRepository.findById(employeeId)
            .orElseThrow { EmployeeNotFoundException(HttpStatus.NOT_FOUND, "No matching employee was found") }

    /**
     * Create employee.
     *
     * @param employee the employee
     * @return the employee
     */
    fun createEmployee(employee: Employee): Employee = employeeRepository.save(employee)

    /**
     * Update employee.
     *
     * @param employeeId the employee id
     * @param employee the employee details
     * @return the employee
     * @throws EmployeeNotFoundException the employee not found exception
     */
    fun updateEmployeeById(employeeId: Long, employee: Employee): Employee {
        return if (employeeRepository.existsById(employeeId)) {
            employeeRepository.save(
                    Employee(
                            id = employee.id,
                            userName = employee.userName,
                            firstName = employee.firstName,
                            middleName = employee.middleName,
                            lastName = employee.lastName,
                            emailId = employee.emailId,
                            dayOfBirth = employee.dayOfBirth
                    )
            )
        } else throw EmployeeNotFoundException(HttpStatus.NOT_FOUND, "No matching employee was found")
    }

    /**
     * Delete employee.
     *
     * @param employeeId the employee id
     * @return the map
     * @throws EmployeeNotFoundException the employee not found exception
     */
    fun deleteEmployeesById(employeeId: Long) {
        return if (employeeRepository.existsById(employeeId)) {
            employeeRepository.deleteById(employeeId)
        } else throw EmployeeNotFoundException(HttpStatus.NOT_FOUND, "No matching employee was found")
    }
}