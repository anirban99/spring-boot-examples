package com.example.api.repository

import com.example.api.repository.model.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository : JpaRepository<Employee, Long>