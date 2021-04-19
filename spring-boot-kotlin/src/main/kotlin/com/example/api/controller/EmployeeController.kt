package com.example.api.controller

import com.example.api.controller.model.EmployeeDto
import com.example.api.service.EmployeeService
import com.example.api.service.model.Employee
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * Controller for REST API endpoints
 */
@RestController
class EmployeeController(private val employeeService: EmployeeService) {

    @GetMapping("/employees")
    fun getAllEmployees(): List<EmployeeDto> {
        return employeeService.getAllEmployees().map {
            EmployeeDto.from(it)
        }
    }

    @GetMapping("/employees/{id}")
    fun getEmployeesById(@PathVariable("id") employeeId: Long): EmployeeDto {
        return EmployeeDto.from(employeeService.getEmployeesById(employeeId))
    }

    @PostMapping("/employees")
    fun createEmployee(@Valid @RequestBody payload: EmployeeDto): EmployeeDto {
        return EmployeeDto.from(employeeService.createEmployee(Employee.from(payload)))
    }

    @PutMapping("/employees/{id}")
    fun updateEmployeeById(@PathVariable("id") employeeId: Long, @Valid @RequestBody payload: EmployeeDto) : EmployeeDto {
        return EmployeeDto.from(employeeService.updateEmployeeById(employeeId, Employee.from(payload)))
    }

    @DeleteMapping("/employees/{id}")
    fun deleteEmployeesById(@PathVariable("id") employeeId: Long): EmployeeDto {
        return EmployeeDto.from(employeeService.deleteEmployeesById(employeeId))
    }
}
