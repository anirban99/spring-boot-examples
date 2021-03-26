package com.example.api.repository.model

//import java.time.Instant
import java.time.LocalDate
import javax.persistence.*

/**
 * Represents the database entity for storing the employee details.
 */
@Entity
@Table(name = "employee")
data class Employee (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,
        @Column(name = "user_name", unique = true, nullable = false)
        val userName: String,
        @Column(name = "first_name", nullable = false)
        val firstName: String,
        @Column(name = "middle_name", nullable = true)
        val middleName: String?,
        @Column(name = "last_name", nullable = false)
        val lastName: String,
        @Column(name = "email_address", nullable = false)
        val emailId: String,
        @Column(name = "day_of_birth", nullable = false)
        val dayOfBirth: LocalDate
//        @Column(name = "created_at", nullable = false)
//        val createdAt: Instant,
//        @Column(name = "last_updated_at", nullable = false)
//        val lastUpdatedAt: Instant = Instant.now()
)