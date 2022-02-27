package com.example.inventory.datasource.database.repository

import com.example.inventory.datasource.database.model.CarEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepository: JpaRepository<CarEntity, Int> {
}