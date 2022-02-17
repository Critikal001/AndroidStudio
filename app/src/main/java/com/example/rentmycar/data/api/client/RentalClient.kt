package com.example.rentmycar.data.api.client

import com.example.rentmycar.data.api.ServiceProvider
import com.example.rentmycar.data.api.request.*
import com.example.rentmycar.data.model.api.post.*
import retrofit2.Response

class RentalClient(private val serviceProvider: ServiceProvider): BaseClient() {

    suspend fun createEngineSpec(engineSpec: EngineSpec): SimpleResponse<EngineSpecRequest>? {
        return safeApiCall { serviceProvider.createEngineSpec(engineSpec) }
    }

    suspend fun createEngine(engine: Engine): SimpleResponse<EngineRequest>? {
        return safeApiCall { serviceProvider.createEngine(engine) }
    }

    suspend fun createCar(car: Car): SimpleResponse<CarRequest>? {
        return safeApiCall { serviceProvider.createCar(car) }
    }

    suspend fun createRental(rental: Rental): SimpleResponse<RentalRequest>? {
        return safeApiCall { serviceProvider.createRental(rental) }
    }

    suspend fun createLocation(location: Location): SimpleResponse<LocationRequest>? {
        return safeApiCall { serviceProvider.createLocation(location) }
    }

    suspend fun createProvider(provider: Provider): SimpleResponse<ProviderRequest>? {
        return safeApiCall { serviceProvider.createProvider(provider) }
    }

    suspend fun createUser(user: User): SimpleResponse<UserRequest>? {
        return safeApiCall { serviceProvider.createUser(user) }
    }

    suspend fun getEngineSpec(): SimpleResponse<List<EngineSpecRequest>>?{
        return safeApiCall { serviceProvider.getEngineSpec() }
    }

    suspend fun getRental(): SimpleResponse<List<RentalRequest>>?{
        return safeApiCall { serviceProvider.getRental() }
    }

    fun getLocation(): SimpleResponse<List<LocationRequest>>? {
        return safeApiCall { serviceProvider.getLocation() }
    }

    fun getRentalByLocation(): SimpleResponse<List<RentalRequest>>?{
        return safeApiCall { serviceProvider.getRentalByLocation() }
    }

    fun getRentalByProvider(): SimpleResponse<List<RentalRequest>>?{
        return safeApiCall { serviceProvider.getRentalByProvider() }
    }

    fun getRentalByCar(): SimpleResponse<List<RentalRequest>>?{
        return safeApiCall { serviceProvider.getRentalByCar() }
    }

    fun getEngine(): SimpleResponse<List<EngineRequest>>?{
        return safeApiCall { serviceProvider.getEngine() }
    }

//    fun deleteCar(id : Int){
//        return safeApiCall { serviceProvider.deleteCar(id) }
//    }

//    fun deleteCustomer(id: Int){
//        return safeApiCall { serviceProvider.deleteCustomer(id) }
//    }

//    fun deleteProvider(id: Int){
//        return safeApiCall { serviceProvider.deleteProvider(id) }
//    }

    fun updateCar(car: Car): SimpleResponse<CarRequest>{
        return safeApiCall { serviceProvider.updateCar(car) }
    }

    fun createCustomer(customer: Customer): SimpleResponse<CustomerRequest>{
        return safeApiCall { serviceProvider.createCustomer(customer) }
    }

    fun updateCustomer(customer: Customer): SimpleResponse<CustomerRequest>{
        return safeApiCall { serviceProvider.updateCustomer(customer) }
    }

    fun updateProvider(provider: Provider): SimpleResponse<ProviderRequest>{
        return safeApiCall { serviceProvider.updateProvider(provider) }
    }

    fun updateRental(rental: Rental): SimpleResponse<RentalRequest>{
        return safeApiCall { serviceProvider.updateRental(rental) }
    }

    fun createReservation(runningRental: RunningRental): SimpleResponse<ReservationRequest>{
        return safeApiCall { serviceProvider.createReservation(runningRental) }
    }

}