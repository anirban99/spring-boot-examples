package com.example.api.repository

import com.example.api.repository.model.EmployeeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Repository


@PreAuthorize("hasRole('ROLE_USER')")
@Repository
interface EmployeeRepository : JpaRepository<EmployeeEntity, Long> {

    override fun findAll(): List<EmployeeEntity>

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun save(employeeEntity: EmployeeEntity): EmployeeEntity

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    override fun delete(employeeEntity: EmployeeEntity)
}
