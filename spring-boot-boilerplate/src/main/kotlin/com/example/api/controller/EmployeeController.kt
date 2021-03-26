package com.example.api.controller

import com.example.api.exception.EmployeeNotFoundException
import com.example.api.repository.EmployeeRepository
import com.example.api.repository.model.Employee
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * Controller for REST API endpoints
 */
@RestController
@RequestMapping("/api/v1")
class EmployeeController(private val employeeRepository: EmployeeRepository) {

    @GetMapping("/employees")
    fun getAllEmployees(): List<Employee> = employeeRepository.findAll()

    @GetMapping("/employees/{id}")
    fun getEmployeesById(@PathVariable("id") employeeId: Long): Employee = employeeRepository.findById(employeeId)
            .orElseThrow { EmployeeNotFoundException(HttpStatus.NOT_FOUND, "No matching employee was found") }

    @PostMapping("/employees")
    fun createEmployee(@RequestBody payload: Employee): Employee = employeeRepository.save(payload)

//    @PutMapping("/employees/{id}")
//    fun updateEmployeeById(@PathVariable("id") employeeId: Long, @Valid @RequestBody payload: Employee): Employee = employeeRepository.update(payload)
//
//    @DeleteMapping("/employees/{id}")
//    fun deleteEmployeesById(@PathVariable ("id") employeeId: Long): Unit = employeeRepository.findById(employeeId).map {
//        employeeRepository.delete(it)
//
//    }
}