package com.example.api.repository.model

import com.example.api.service.model.Employee
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

/**
 * Represents the database entity for storing the employee details.
 */
@Entity
@Table(name = "employees")
data class EmployeeEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        @Column(name = "user_name", unique = true, nullable = false)
        val userName: String,
        @Column(name = "first_name", nullable = false)
        val firstName: String = "",
        @Column(name = "middle_name", nullable = true)
        val middleName: String? = null,
        @Column(name = "last_name", nullable = false)
        val lastName: String = "",
        @Column(name = "email_address", nullable = false)
        val emailId: String = "",
        @Column(name = "day_of_birth", nullable = false)
        val dayOfBirth: LocalDate
) {
    companion object ModelMapper {
        fun from(employee: Employee): EmployeeEntity {
            return EmployeeEntity(
                    id = employee.id,
                    userName = employee.userName,
                    firstName = employee.firstName,
                    middleName = employee.middleName,
                    lastName = employee.lastName,
                    emailId = employee.emailId,
                    dayOfBirth = employee.dayOfBirth
            )
        }
    }
}
