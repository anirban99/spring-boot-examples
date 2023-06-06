package com.example.windpark.datasource.database.repository

import com.example.windpark.datasource.database.model.WindPark
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WindParkRepository: JpaRepository<WindPark, String>