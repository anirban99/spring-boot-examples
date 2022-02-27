package com.example.schedule.service

import com.example.schedule.controller.model.OpeningHours
import com.example.schedule.exception.InvalidOpeningHoursException
import com.example.schedule.utils.Type.OPEN
import com.example.schedule.utils.Type.CLOSE
import org.springframework.stereotype.Service
import java.time.DayOfWeek
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Service
class RestaurantService {

    /**
     * Returns a formatted list of schedule as String
     */
    fun createSchedule(schedule: Map<DayOfWeek, List<OpeningHours>>): String {

        return schedule.map { (key, value) ->
            val dayOfWeek = key.name.lowercase().replaceFirstChar { it.uppercase() }
            val openingHours =
                if (value.isEmpty()) "Closed" else fetchOpeningHours(key, value, schedule)
            "$dayOfWeek: $openingHours"
        }.joinToString(System.lineSeparator())
    }

    /**
     * Returns the Opening Hours for every day of the week
     */
    private fun fetchOpeningHours(
        dayOfWeek: DayOfWeek, openingHours: List<OpeningHours>, schedule: Map<DayOfWeek, List<OpeningHours>>
    ): String {
        val openingHoursList = openingHours.sortedBy { it.value }.toMutableList()
        if (openingHoursList.first().type == CLOSE) openingHoursList.removeFirst()

        if (openingHoursList.last().type == OPEN) {
            val nextDayOpeningHours = schedule[dayOfWeek.plus(1)]
            if (nextDayOpeningHours.isNullOrEmpty()) throw InvalidOpeningHoursException("Opening hours list is empty or null")
            val sortedNextDayOpeningHours = nextDayOpeningHours.sortedBy { it.value }
            if (sortedNextDayOpeningHours.first().type == OPEN) throw InvalidOpeningHoursException("Opening hours list is invalid")
            openingHoursList.add(sortedNextDayOpeningHours.first())
        }

        return openingHoursList.withIndex().zipWithNext { a, b ->
            if (a.index % 2 == 0) {
                getFormattedTime(a.value.value, b.value.value)
            } else ""
        }.filter { it.isNotBlank() }.joinToString(", ")
    }

    /**
     * Returns the opening and closing time as String
     */
    private fun getFormattedTime(openingTime: Long, closingTime: Long): String {
        return "${openingTime.format()} - ${closingTime.format()}"
    }

    /**
     * Converts a Unix time to Local Time and returns as a String as an AM/PM format
     */
    private fun Long.format(): String = LocalTime.ofSecondOfDay(this)
        .format(DateTimeFormatter.ofPattern("h:mm a")).replace(":00", "")

}