package com.example.windpark.integration

import com.example.windpark.datasource.database.model.WindPark
import com.example.windpark.datasource.database.repository.WindParkRepository
import com.example.windpark.util.WindParkFaker
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@DataJpaTest
class WindParkRepositoryDataJpaTest {

    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var windParkRepository: WindParkRepository

    @Test
    fun `Given empty repository, When no wind park is found, Then should return empty`() {
        val windParkList  = windParkRepository.findAll()

        assertThat(windParkList).isEmpty()
    }

    @Test
    fun `Given wind park, When Save is requested, Then should save one wind park`(){
        val windParkId = "aff8c6b2-3919-4847-b41d-9c4182832841"
        val windPark = windParkRepository.save(WindParkFaker.fakeWindPark(id = windParkId))

        assertThat(windPark).isNotNull
        assertThat(windPark.id).isEqualTo(windParkId)
    }

    @Test
    fun `Given wind park collection, When SaveAll is requested, Then should save wind park list`(){
        val windParkList = windParkRepository.saveAll(WindParkFaker.fakeWindParkCollection())

        assertThat(windParkList).isNotEmpty
        assertThat(windParkList).hasSize(5)
    }

    @Nested
    inner class FindAndDeleteWindParks {

        @BeforeEach
        fun setUp() {
            val firstWindParkFaker = WindParkFaker.fakeWindPark()
            val secondWindParFaker = WindParkFaker.fakeWindPark(
                id = "20e1cb09-5403-4bed-86f4-19da202f2dbe",
                windPark = "PPA",
                powerProduced = 6.88,
                period = "30m",
                timestamp = "2020-05-17T00:30:00Z",
                createdOn = "2020-05-20T13:17:00Z",
                updatedOn = "2020-05-20T13:17:00Z"
            )
            entityManager.persist(firstWindParkFaker)
            entityManager.persist(secondWindParFaker)
        }

        @Test
        fun `Given wind parks collection, When FindByAll is requested, Then should return wind park list`() {
            val windParkList  = windParkRepository.findAll()

            assertThat(windParkList).isNotEmpty
            assertThat(windParkList).hasSize(2)
        }

        @Test
        fun `Given wind parks collection, When FindById is requested, Then should return one wind park`() {
            val windParkId = "aff8c6b2-3919-4847-b41d-9c4182832841"

            val windPark = windParkRepository.findById(windParkId).get()

            assertThat(windPark).isNotNull
            assertThat(windPark.id).isEqualTo(windParkId)
        }

        @Test
        fun `Given wind parks collection, When update power produced by wind park id is requested, Then should update one wind park`(){
            val windParkId = "aff8c6b2-3919-4847-b41d-9c4182832841"
            val updatedPowerProduced = "9.75"
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:00'Z'")
            val updatedDateTime = LocalDateTime.now(ZoneId.of("UTC")).format(formatter)
            val windPark = windParkRepository.findById(windParkId).get()

            windParkRepository.save(
                WindPark(
                    id = windPark.id,
                    windPark = windPark.windPark,
                    powerProduced = updatedPowerProduced.toDouble(),
                    period = windPark.period,
                    timestamp = windPark.timestamp,
                    createdOn = windPark.createdOn,
                    updatedOn = updatedDateTime
                )
            )

            val updatedWindPark = windParkRepository.findById(windParkId).get()

            assertThat(updatedWindPark.id).isEqualTo(windParkId)
            assertThat(updatedWindPark.powerProduced).isEqualTo(updatedPowerProduced.toDouble())
            assertThat(updatedWindPark.updatedOn).isEqualTo(updatedDateTime)
        }

        @Test
        fun `Given wind parks collection, When DeleteById is requested, Then should delete one wind park`(){
            val windParkId = "aff8c6b2-3919-4847-b41d-9c4182832841"
            windParkRepository.deleteById(windParkId)

            val windParkList  = windParkRepository.findAll()

            assertThat(windParkList).hasSize(1)
        }

    }
}