package com.example.rentmycar.data.api.client

import com.example.rentmycar.data.api.ServiceProvider
import com.example.rentmycar.data.api.request.SimpleResponse
import com.example.rentmycar.data.model.api.post.*

class RentalClient(private val serviceProvider: ServiceProvider): BaseClient() {

    suspend fun createEngineSpec(engineSpec: EngineSpec): SimpleResponse<EngineSpec> {
        return safeApiCall { serviceProvider.createEngineSpec(engineSpec) }
    }

    suspend fun createEngine(engine: Engine): SimpleResponse<Engine> {
        return safeApiCall { serviceProvider.createEngine(engine) }
    }

    suspend fun createCar(car: Car): SimpleResponse<Car> {
        return safeApiCall { serviceProvider.createCar(car) }
    }

    suspend fun createRental(rental: Rental): SimpleResponse<Rental> {
        return safeApiCall { serviceProvider.createRental(rental) }
    }

    suspend fun createLocation(location: Location): SimpleResponse<Location> {
        return safeApiCall { serviceProvider.createLocation(location) }
    }

    suspend fun createProvider(provider: Provider): SimpleResponse<Provider> {
        return safeApiCall { serviceProvider.createProvider(provider) }
    }

    suspend fun createUser(user: User): SimpleResponse<User> {
        return safeApiCall { serviceProvider.createUser(user) }
    }

}