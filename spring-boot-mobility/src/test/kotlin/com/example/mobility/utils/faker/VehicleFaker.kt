package com.example.mobility.utils.faker

import com.example.mobility.vehicle.datasource.model.Position
import com.example.mobility.vehicle.datasource.model.VehicleResponse
import com.example.mobility.vehicle.service.model.PositionDetails
import com.example.mobility.vehicle.service.model.Vehicle
import com.example.mobility.vehicle.utils.Model

class VehicleFaker {
    companion object{

        fun fakeVehicleResponseList(): List<VehicleResponse> {
            return listOf(
//                fakeVehicleResponse(),
                fakeVehicleResponse(
                    id = 2,
                    locationId = 3,
                    vin = "1GTP2VF35BLFY73SD",
                    numberPlate = "S-SI6906",
                    position = Position(
                        longitude = 48.78738839903186.toBigDecimal(),
                        latitude = 9.203876627904897.toBigDecimal()),
                    fuel = 0.0,
                    model = Model.DELOREAN
                ),
                fakeVehicleResponse(
                    id = 3,
                    locationId = 3,
                    vin = "KMHDB85E39CMCGTWF",
                    numberPlate = "S-JY1108",
                    position = Position(
                        longitude = 48.808060668552024.toBigDecimal(), //48.71977081860343
                        latitude = 9.218029628326825.toBigDecimal()), //9.179287448533215
                    fuel = 0.7,
                    model = Model.SMART_42_ELECTRIC
                ),
                fakeVehicleResponse()
            )
        }

        private fun fakeVehicleResponse(
            id: Int = 1,
            locationId: Int= 3,
            vin: String = "WAUSH54F08AAS7GT5",
            numberPlate: String = "S-UR9224",
            position: Position = Position(
                longitude = 48.808060668552024.toBigDecimal(),
                latitude = 9.218029628326825.toBigDecimal()),
            fuel:Double = 0.8,
            model:Model = Model.SMART_42_PASSION
        ): VehicleResponse {
            return VehicleResponse(
                id = id,
                locationId = locationId,
                vin = vin,
                numberPlate = numberPlate,
                position = position,
                fuel = fuel,
                model = model
            )
        }

        private fun fakeVehicle(
            polygonId: String = "",
            id: Int = 1,
            locationId: Int= 3,
            vin: String = "WAUSH54F08AAS7GT5",
            numberPlate: String = "S-UR9224",
            position: PositionDetails = PositionDetails(
                longitude = 48.808060668552024.toBigDecimal(),
                latitude = 9.218029628326825.toBigDecimal()),
            fuel:Double = 0.8,
            model:Model = Model.SMART_42_PASSION
        ): Vehicle {
            return Vehicle(
                polygonId = polygonId,
                id = id,
                locationId = locationId,
                vin = vin,
                numberPlate = numberPlate,
                position = position,
                fuel = fuel,
                model = model
            )
        }
    }
}