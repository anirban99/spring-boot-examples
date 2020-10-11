package com.example.api.controller.model

import com.example.api.service.model.Employee

/**
 * Represents the DTO which is exposed to the clients through REST endpoints.
 */
data class EmployeeDto(
        val id: Long,
        val firstName: String,
        val middleName: String?,
        val lastName: String,
        val emailId: String
) {
    companion object ModelMapper {
        fun from(employee: Employee): EmployeeDto {
            return EmployeeDto(
                    id = employee.id,
                    firstName = employee.firstName,
                    middleName = employee.middleName,
                    lastName = employee.lastName,
                    emailId = employee.emailId
            )
        }
    }
}