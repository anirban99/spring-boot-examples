package com.example.api.utils.faker

import com.example.api.repository.model.EmployeeEntity
import com.example.api.service.model.Employee
//import com.github.javafaker.Faker

class EmployeeFaker {
    companion object {
        private val id = Math.random().toLong()
        private val emailId = Math.random()

        fun fakeEmployee(): Employee {
//            val javaFaker = Faker()
            return Employee(
                    id = id,
                    firstName = "Brock",
                    middleName = "",
                    lastName = "Lesnar",
                    emailId = "Brock.Lesnar$emailId@gmail.com"
            )
        }

        fun fakeEmployeeEntity(): EmployeeEntity {
//            val javaFaker = Faker()
            return EmployeeEntity(
                    id = id,
                    firstName = "Brock",
                    middleName = "",
                    lastName = "Lesnar",
                    emailId = "Brock.Lesnar$emailId@gmail.com"
            )
        }

        fun fakeUpdatedEmployee(): Employee {
            return Employee(
                    id = id,
                    firstName = "Shawn",
                    middleName = "Michaels",
                    lastName = "Lesnar",
                    emailId = "shawn.michaels@gmail.com"
            )
        }

        fun fakeUpdatedEmployeeEntity(): EmployeeEntity {
            return EmployeeEntity(
                    id = id,
                    firstName = "Shawn",
                    middleName = "Michaels",
                    lastName = "Lesnar",
                    emailId = "shawn.michaels@gmail.com"
            )
        }
    }
}
