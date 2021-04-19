package com.example.api.utils.faker

import com.example.api.repository.model.EmployeeEntity
import com.example.api.service.model.Employee
import java.time.LocalDate

//import com.github.javafaker.Faker

class EmployeeFaker {
    companion object {
        private val id = Math.random().toLong()
        private val emailId = Math.random()

        fun fakeEmployee(): Employee {
//            val javaFaker = Faker()
            return Employee(
                    id = id,
                    userName = "john.doe",
                    firstName = "John",
                    middleName = "",
                    lastName = "Doe",
                    emailId = "john.doe$emailId@gmail.com",
                    dayOfBirth = LocalDate.now()
            )
        }

        fun fakeEmployeeEntity(): EmployeeEntity {
//            val javaFaker = Faker()
            return EmployeeEntity(
                    id = id,
                    userName = "john.doe",
                    firstName = "John",
                    middleName = "",
                    lastName = "Doe",
                    emailId = "john.doe$emailId@gmail.com",
                    dayOfBirth = LocalDate.now()
            )
        }

        fun fakeUpdatedEmployee(): Employee {
            return Employee(
                    id = id,
                    userName = "john.doe",
                    firstName = "John",
                    middleName = "Michaels",
                    lastName = "Doe",
                    emailId = "john.doe@gmail.com",
                    dayOfBirth = LocalDate.now()
            )
        }

        fun fakeUpdatedEmployeeEntity(): EmployeeEntity {
            return EmployeeEntity(
                    id = id,
                    userName = "john.doe",
                    firstName = "John",
                    middleName = "Michaels",
                    lastName = "Doe",
                    emailId = "john.doe@gmail.com",
                    dayOfBirth = LocalDate.now()
            )
        }
    }
}
