package com.example.schedule.unit

import com.example.schedule.faker.ScheduleFaker
import com.example.schedule.service.RestaurantService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

@WebMvcTest
class RestaurantControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var restaurantService: RestaurantService

    @Test
    fun `Given valid schedule, when POST restaurant is called, then returns formatted restaurant schedule`() {
        val scheduleMap = ScheduleFaker.fakeScheduleMap()
        val schedule = ScheduleFaker.fakeSchedule()
        `when`(restaurantService.createSchedule(scheduleMap)).thenReturn(schedule)

        mockMvc.perform(
            post("/restaurants")
                .content(objectMapper.writeValueAsString(scheduleMap))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType("text/plain;charset=UTF-8"))
            .andExpect(content().string(schedule))
    }
}