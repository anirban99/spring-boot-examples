package com.example.schedule.unit

import com.example.schedule.exception.InvalidOpeningHoursException
import com.example.schedule.faker.ScheduleFaker
import com.example.schedule.service.RestaurantService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RestaurantServiceTest {

    private val classUnderTest = RestaurantService()

    @Test
    fun `Given a map of schedule, When schedule is created, Then returns a string of schedules`() {
        val result = classUnderTest.createSchedule(ScheduleFaker.fakeScheduleMap())

        assertEquals(result, ScheduleFaker.fakeSchedule())
    }

    @Test
    fun `Given a map containing empty opening hours list, When schedule is requested, Then throws InvalidOpeningHoursException`() {

        assertThrows<InvalidOpeningHoursException> { classUnderTest.createSchedule(ScheduleFaker.fakeScheduleMapWithEmptyOpeningHours()) }
    }

    @Test
    fun `Given a map containing invalid opening hours list, When schedule is requested, Then throws InvalidOpeningHoursException`() {

        assertThrows<InvalidOpeningHoursException> { classUnderTest.createSchedule(ScheduleFaker.fakeScheduleMapWithInvalidOpeningHours()) }
    }
}