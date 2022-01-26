package com.example.rentmycar.data.repositories


import com.example.rentmycar.data.api.ServiceProvider
import com.example.rentmycar.data.model.api.post.*
import com.example.rentmycar.data.model.api.request.GetRental
import retrofit2.Response


class RentalRepository {

    companion object {
        lateinit var api: ServiceProvider.TodoApi

        fun getRentalList(): List<GetRental> {
            return api.retrofitService.getRentalList()
        }

        fun createRental(rental: Rental): Rental{
            return api.retrofitService.createRental(rental)
        }

        fun createCar(car: Car): Car{
            return api.retrofitService.createCar(car)
        }

        fun createEngine(engine:Engine): Engine{
            return api.retrofitService.createEngine(engine)
        }

        fun createEngineSpec(engineSpec: EngineSpec): EngineSpec {
            return api.retrofitService.createEngineSpec(engineSpec)
        }
        fun createUser(user: User):User{
            return api.retrofitService.createUser(user)
        }

        fun createProvider(provider: Provider): Provider {
            return api.retrofitService.createProvider(provider)
        }
        fun createLocation(location: Location) :Location{
            return api.retrofitService.createLocation(location)
        }



    }

}