package com.example.api.service.model

import com.example.api.controller.model.EmployeeDto
import com.example.api.repository.model.EmployeeEntity

/**
 * Represents the domain model for an employee in the service layer.
 */
data class Employee(
        val id: Long,
        val firstName: String,
        val middleName: String?,
        val lastName: String,
        val emailId: String
) {
    companion object ModelMapper {
        fun from(employeeEntity: EmployeeEntity): Employee {
            return Employee(
                    id = employeeEntity.id,
                    firstName = employeeEntity.firstName,
                    middleName = employeeEntity.middleName,
                    lastName = employeeEntity.lastName,
                    emailId = employeeEntity.emailId
            )
        }

        fun from(employeeDto: EmployeeDto): Employee {
            return Employee(
                    id = employeeDto.id,
                    firstName = employeeDto.firstName,
                    middleName = employeeDto.middleName,
                    lastName = employeeDto.lastName,
                    emailId = employeeDto.emailId
            )
        }
    }
}
