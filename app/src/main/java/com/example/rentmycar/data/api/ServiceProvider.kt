package com.example.rentmycar.data.api

import com.example.rentmycar.data.api.client.RentalClient
import com.example.rentmycar.data.model.api.post.*
import com.example.rentmycar.data.api.request.GetRental
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL =
    "http://10.0.2.2:8080/"
//    "http://10.0.2.2:8080/"

// For parsing the json result: add a Moshi builder
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    // A converter for strings and both primitives and their boxed types to text/plain bodies.

    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// Here we define how Retrofit interacts with the webservice
// we create 'suspend' fun, so we can call the function from a coroutine scope

interface ServiceProvider {
    //    // All getters from the api
    @GET("engine/get-engine-spec")
    suspend fun getEngineSpec(): List<EngineSpec>

    @GET("location/get-locationbyid/all")
    fun getLocation(): List<Location>

    @GET("rental/get-by-location")
    fun getRentalByLocation(): List<GetRental>

    @GET("rental/get-by-provider")
    fun getRentalByProvider(): List<GetRental>

    @GET("rental/get-rental-by-car")
    fun getRentalByCar(): List<GetRental>

    @GET("rental/get-rental")
    fun getRentalList(): Response<List<GetRental>>

    //All deletes from the api
    @DELETE("car/delete-car/{id}")               
    fun deleteCar(
        @Path("id") id: Int,
    )

    @DELETE("customer/delete-customer/{id}")
    fun deleteCustomer(
        @Path("id") id: Int,
    )

    @DELETE("provider/delete-provider/{id}")
    fun deleteProvider(
        @Path("id") id: Int,
    )

 //there need to come more deletes

    //All posts from the api
    
    @POST("car/create-car")
    fun createCar(
        @Body car: Car
    ):Response<Car>

    
    @POST("car/update-car")
    fun updateCar(
        @Body car: Car
    ): Car

    
    @POST("customer/create-customer")
    fun createCustomer(
        @Body customer: Customer
    ) :Customer

    
    @POST("customer/update-customer")
    fun updateCustomer(
        @Body customer: Customer
    ) :Customer
    
    
    @POST("engine/create-engine")
    fun createEngine(
        @Body engine: Engine
    ) :Response<Engine>
    
    
    @POST("engine/create-enginespec")
    fun createEngineSpec(
        @Body engineSpec: EngineSpec): Response<EngineSpec>


    
    @POST("location/create-location")
    fun createLocation(
        @Body location: Location
    ) :Response<Location>
    
    
    @POST("provider/create-provider")
    fun createProvider(
        @Body provider: Provider
    ) :Response<Provider>
    
    
    @POST("provider/update-provider")
    fun updateProvider(
        @Body provider: Provider
    ) :Provider
    
    
    @POST("rental/create-rental")
    fun createRental(
        @Body rental: Rental
    ) :Response<Rental>
    
    
    @POST("rental/update-rental")
    fun updateRental(
        @Body rental: Rental
    ) :Rental
    
    
    @POST("runningRental/rent-car")
    fun createReservation(
        @Body runningRental: RunningRental
    ) :RunningRental
    
    
    @POST("user/create-user")
    fun createUser(
        @Body user: User
    ) : Response<User>
    
    object RentalApi {
        val retrofitService: ServiceProvider by lazy {
            retrofit.create(ServiceProvider::class.java)
        }
        val rentalClient = RentalClient(retrofitService)
    }
}