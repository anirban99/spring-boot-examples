package com.example.api.service.model

import com.example.api.controller.model.EmployeeDto
import com.example.api.repository.model.EmployeeEntity
import java.time.LocalDate

/**
 * Represents the domain model for an employee in the service layer.
 */
data class Employee(
        val id: Long,
        val userName: String,
        val firstName: String,
        val middleName: String?,
        val lastName: String,
        val emailId: String,
        val dayOfBirth: LocalDate
) {
    companion object ModelMapper {
        fun from(employeeEntity: EmployeeEntity): Employee {
            return Employee(
                    id = employeeEntity.id,
                    userName = employeeEntity.userName,
                    firstName = employeeEntity.firstName,
                    middleName = employeeEntity.middleName,
                    lastName = employeeEntity.lastName,
                    emailId = employeeEntity.emailId,
                    dayOfBirth = employeeEntity.dayOfBirth
            )
        }

        fun from(employeeDto: EmployeeDto): Employee {
            return Employee(
                    id = employeeDto.id,
                    userName = employeeDto.userName,
                    firstName = employeeDto.firstName,
                    middleName = employeeDto.middleName,
                    lastName = employeeDto.lastName,
                    emailId = employeeDto.emailId,
                    dayOfBirth = employeeDto.dayOfBirth

            )
        }
    }
}
