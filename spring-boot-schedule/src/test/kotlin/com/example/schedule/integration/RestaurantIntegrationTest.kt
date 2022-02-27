package com.example.schedule.integration

import com.example.schedule.faker.ScheduleFaker
import com.example.schedule.service.RestaurantService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

@SpringBootTest
@AutoConfigureMockMvc
class RestaurantIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var restaurantService: RestaurantService

    @Test
    fun `Given valid schedule, when POST request is called, then returns formatted restaurant schedule`() {
        val scheduleMap = ScheduleFaker.fakeScheduleMap()
        val schedule = ScheduleFaker.fakeSchedule()
        Mockito.`when`(restaurantService.createSchedule(ScheduleFaker.fakeScheduleMap())).thenReturn(schedule)

        mockMvc.perform(
            post("/restaurants")
                .content(objectMapper.writeValueAsString(scheduleMap))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith("text/plain;charset=UTF-8"))
            .andExpect(content().string(schedule))
    }

    @Test
    fun `Given invalid schedule json, when POST request is called, then throws Exception`() {
        mockMvc.perform(
            post("/restaurants")
                .content(ScheduleFaker.fakeInvalidScheduleJson())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest)

        Mockito.verifyNoInteractions(restaurantService)
    }

    @Test
    fun `Given invalid request body, when POST request is called, then throws Exception`() {
        mockMvc.perform(
            post("/restaurants")
                .content("Invalid request body")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest)

        Mockito.verifyNoInteractions(restaurantService)
    }

    @Test
    fun `Given invalid media type as request body, when POST request is called, then throws Exception`() {
        mockMvc.perform(
            post("/restaurants")
                .content(ScheduleFaker.fakeScheduleJson())
                .contentType(MediaType.TEXT_PLAIN)
        ).andExpect(status().isUnsupportedMediaType)

        Mockito.verifyNoInteractions(restaurantService)
    }
}