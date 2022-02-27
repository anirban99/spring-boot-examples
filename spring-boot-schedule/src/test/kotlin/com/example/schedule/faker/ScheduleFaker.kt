package com.example.schedule.faker

import com.example.schedule.controller.model.OpeningHours
import com.example.schedule.utils.Type.OPEN
import com.example.schedule.utils.Type.CLOSE
import java.time.DayOfWeek

class ScheduleFaker {
    companion object {

        fun fakeScheduleMap() = mapOf(
            DayOfWeek.MONDAY to listOf(),
            DayOfWeek.TUESDAY to listOf(
                OpeningHours(OPEN, 36000),
                OpeningHours(CLOSE, 64800)
            ),
            DayOfWeek.WEDNESDAY to listOf(
                OpeningHours(OPEN, 36000),
                OpeningHours(CLOSE, 50000),
                OpeningHours(OPEN, 64800),
                OpeningHours(CLOSE, 75600)
            ),
            DayOfWeek.THURSDAY to listOf(
                OpeningHours(OPEN, 37800),
                OpeningHours(CLOSE, 64800)
            ),
            DayOfWeek.FRIDAY to listOf(
                OpeningHours(OPEN, 36000)
            ),
            DayOfWeek.SATURDAY to listOf(
                OpeningHours(OPEN, 36000),
                OpeningHours(CLOSE, 3600)
            ),
            DayOfWeek.SUNDAY to listOf(
                OpeningHours(CLOSE, 3600),
                OpeningHours(OPEN, 43200),
                OpeningHours(CLOSE, 75600)
            )
        )

        fun fakeScheduleMapWithEmptyOpeningHours() = mapOf(
            DayOfWeek.MONDAY to listOf(),
            DayOfWeek.TUESDAY to listOf(
                OpeningHours(OPEN, 36000),
                OpeningHours(CLOSE, 64800)
            ),
            DayOfWeek.WEDNESDAY to listOf(
                OpeningHours(OPEN, 36000),
                OpeningHours(CLOSE, 50000),
                OpeningHours(OPEN, 64800),
                OpeningHours(CLOSE, 75600)
            ),
            DayOfWeek.THURSDAY to listOf(
                OpeningHours(OPEN, 37800),
                OpeningHours(CLOSE, 64800)
            ),
            DayOfWeek.FRIDAY to listOf(
                OpeningHours(OPEN, 36000)
            ),
            DayOfWeek.SATURDAY to listOf(),
            DayOfWeek.SUNDAY to listOf(
                OpeningHours(CLOSE, 3600),
                OpeningHours(OPEN, 43200),
                OpeningHours(CLOSE, 75600)
            )
        )

        fun fakeScheduleMapWithInvalidOpeningHours() = mapOf(
            DayOfWeek.MONDAY to listOf(),
            DayOfWeek.TUESDAY to listOf(
                OpeningHours(OPEN, 36000),
                OpeningHours(CLOSE, 64800)
            ),
            DayOfWeek.WEDNESDAY to listOf(
                OpeningHours(OPEN, 36000),
                OpeningHours(CLOSE, 50000),
                OpeningHours(OPEN, 64800),
                OpeningHours(CLOSE, 75600)
            ),
            DayOfWeek.THURSDAY to listOf(
                OpeningHours(OPEN, 37800),
                OpeningHours(CLOSE, 64800)
            ),
            DayOfWeek.FRIDAY to listOf(
                OpeningHours(OPEN, 36000)
            ),
            DayOfWeek.SATURDAY to listOf(
                OpeningHours(OPEN, 36000)
            ),
            DayOfWeek.SUNDAY to listOf(
                OpeningHours(CLOSE, 3600),
                OpeningHours(OPEN, 43200),
                OpeningHours(CLOSE, 75600)
            )
        )

        fun fakeInvalidScheduleJson() =
            """
            {
            "monday" : [],
            "funday" : [
                {
                  "type" : "open",
                  "value" : 36000
                },
                {
                  "type" : "close",
                  "value" : 64800
                }
              ],
            "wednesday" : [],
            "thursday" : [],
            "friday" : [],
            "saturday" : [],
            "sunday" : []
            }   
            """.trimIndent()

        fun fakeScheduleJson() =
            """
            {
              "monday" : [],
              "tuesday" : [
                {
                  "type" : "open",
                  "value" : 36000
                },
                {
                  "type" : "close",
                  "value" : 64800
                }
              ],
              "wednesday" : [
                {
                  "type" : "open",
                  "value" : 36000
                },
                {
                  "type" : "close",
                  "value" : 50000
                },
                {
                  "type" : "open",
                  "value" : 64800
                },
                {
                  "type" : "close",
                  "value" : 75600
                }
              ],
              "thursday" : [
                {
                  "type" : "open",
                  "value" : 37800
                },
                {
                  "type" : "close",
                  "value" : 64800
                }
              ],
              "friday" : [
                {
                  "type" : "open",
                  "value" : 36000
                }
              ],
              "saturday" : [
                {
                  "type" : "close",
                  "value" : 3600
                },
                {
                  "type" : "open",
                  "value" : 36000
                }
              ],
              "sunday" : [
                {
                  "type" : "close",
                  "value" : 3600
                },
                {
                  "type" : "open",
                  "value" : 43200
                },
                {
                  "type" : "close",
                  "value" : 75600
                }
              ]
            }
            """.trimIndent()

        fun fakeSchedule() =
            """
            Monday: Closed
            Tuesday: 10 AM - 6 PM
            Wednesday: 10 AM - 1:53 PM, 6 PM - 9 PM
            Thursday: 10:30 AM - 6 PM
            Friday: 10 AM - 1 AM
            Saturday: 10 AM - 1 AM
            Sunday: 12 PM - 9 PM
            """.trimIndent()
    }
}