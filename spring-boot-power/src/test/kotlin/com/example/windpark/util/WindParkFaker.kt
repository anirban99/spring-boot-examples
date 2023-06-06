package com.example.windpark.util

import com.example.windpark.datasource.database.model.WindPark

class WindParkFaker {

    companion object {

        fun fakeWindParkCollection(): List<WindPark> {
            return listOf(
                fakeWindPark(),
                fakeWindPark(
                    id = "20e1cb09-5403-4bed-86f4-19da202f2dbe",
                    windPark = "PPA",
                    powerProduced = 6.88,
                    period = "30m",
                    timestamp = "2020-05-17T00:30:00Z",
                    createdOn = "2020-05-20T13:17:00Z",
                    updatedOn = "2020-05-20T13:17:00Z"
                ),
                fakeWindPark(
                    id = "c73b5189-a883-43cc-9b64-3954285b7569",
                    windPark = "QMA",
                    powerProduced = 6.85,
                    period = "30m",
                    timestamp = "2020-05-17T01:00:00Z",
                    createdOn = "2020-05-20T13:17:00Z",
                    updatedOn = "2020-05-20T13:17:00Z"
                ),
                fakeWindPark(
                    id = "36b74a41-74cc-4fe5-9f79-c3163a31253f",
                    windPark = "PPA",
                    powerProduced = 7.1,
                    period = "30m",
                    timestamp = "2020-05-17T01:30:00Z",
                    createdOn = "2020-05-20T13:17:00Z",
                    updatedOn = "2020-05-20T13:17:00Z"
                ),
                fakeWindPark(
                    id = "f665b130-e892-4403-81f5-cffd0b075780",
                    windPark = "DKX",
                    powerProduced = 5.79,
                    period = "30m",
                    timestamp = "2020-05-17T02:00:00Z",
                    createdOn = "2020-05-20T13:17:00Z",
                    updatedOn = "2020-05-20T13:17:00Z"
                )
            )
        }

        fun fakeWindPark(
            id: String = "aff8c6b2-3919-4847-b41d-9c4182832841",
            windPark: String = "DKX",
            powerProduced: Double = 6.75,
            period: String = "30m",
            timestamp: String = "2020-05-17T00:00:00Z",
            createdOn: String = "2020-05-20T13:17:00Z",
            updatedOn: String = "2020-05-20T13:17:00Z"
        ): WindPark {
            return WindPark (
                id = id,
                windPark = windPark,
                powerProduced = powerProduced,
                period = period,
                timestamp = timestamp,
                createdOn = createdOn,
                updatedOn = updatedOn
            )
        }
    }
}