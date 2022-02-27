package com.example.schedule.controller

import com.example.schedule.controller.model.OpeningHours
import com.example.schedule.service.RestaurantService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.DayOfWeek

@RestController
class RestaurantController(private val restaurantService: RestaurantService) {

    /**
     * A Rest Controller to return a formatted string of Opening Times
     */
    @PostMapping("/restaurants")
    fun createSchedule(@RequestBody schedule: Map<DayOfWeek, List<OpeningHours>>): String {
        return restaurantService.createSchedule(schedule)
    }
}