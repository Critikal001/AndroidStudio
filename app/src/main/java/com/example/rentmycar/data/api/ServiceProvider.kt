package com.example.rentmycar.data.api

import com.example.rentmycar.data.api.client.RentalClient
import com.example.rentmycar.data.api.request.*
import com.example.rentmycar.data.model.api.post.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
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
// we create 'suspend' fun, so we can Response the function from a coroutine scope

interface ServiceProvider {
    //    // All getters from the api
    @GET("engine/get-engine-spec")
    suspend fun getEngineSpec(): Response<List<EngineSpecRequest>>

    @GET("location/get-locationbyid/all")
    fun getLocation(): Response<List<LocationRequest>>


    @GET("user/get-user/{userName}")
    fun getUser(@Path("userName") userName : String): Call<UserRequest>

    @GET("rental/get-rental-by-id/{rentalId}")
    fun getRentalById(@Path("rentalId") rentalId : Integer): Call<RentalRequest>

    @GET("rentalplan/get-rentalplan/{rentalplanId}")
    fun getRentalPlan(@Path("rentalplanId") rentalplanId : Integer): Call<RentalPlan>

    @GET("rental/get-by-provider")
    fun getRentalByProvider(): Response<List<RentalRequest>>

    @GET("rental/get-rental-by-car")
    fun getRentalByCar(): Response<List<RentalRequest>>

    @GET("rental/get-rental")
    fun getRentalList(): Response<List<RentalRequest>>

    @GET("engine/get-engine")
    fun getEngine(): Response<List<EngineRequest>>

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
    ):Call<CarRequest>

    
    @POST("car/update-car")
    fun updateCar(
        @Body car: Car
    ): Response<CarRequest>

    
    @POST("customer/create-customer")
    fun createCustomer(
        @Body customer: Customer
    ) :Response<CustomerRequest>

    
    @POST("customer/update-customer")
    fun updateCustomer(
        @Body customer: Customer
    ) :Response<CustomerRequest>
    
    
    @POST("engine/create-engine")
    fun createEngine(
        @Body engine: Engine
    ) :Call<EngineRequest>
    
    
    @POST("engine/create-enginespec")
    fun createEngineSpec(
        @Body engineSpec: EngineSpec): Call<EngineSpecRequest>


    
    @POST("location/create-location")
    fun createLocation(
        @Body location: Location
    ) :Call<LocationRequest>
    
    
    @POST("provider/create-provider")
    fun createProvider(
        @Body provider: Provider
    ) :Response<ProviderRequest>
    
    
    @POST("provider/update-provider")
    fun updateProvider(
        @Body provider: Provider
    ) :Response<ProviderRequest>

    @POST("rental/create-rental")
    fun createRental(
        @Body rental: Rental
    ) :Call<RentalRequest>

    @POST("rentalplan/create-rentalplan")
    fun createRentalPlan(
        @Body rental: Rental
    ) :Call<RentalPlan>
    
    @POST("rental/update-rental")
    fun updateRental(
        @Body rental: Rental
    ) :Response<RentalRequest>
    
    
    @POST("runningRental/rent-car")
    fun createReservation(
        @Body runningRental: RunningRental
    ) :Response<ReservationRequest>

    @POST("runningRental/rent-car")
    fun getRental() :Response<List<RentalRequest>>
    
    @POST("user/create-user")
    fun createUser(
        @Body user: User
    ) : Call<UserRequest>

    @POST("image/create-image")
    fun createImage(
        @Body images: Images
    ) : Call<Images>


    
    object RentalApi {
        val retrofitService: ServiceProvider by lazy {
            retrofit.create(ServiceProvider::class.java)
        }
        val rentalClient = RentalClient(retrofitService)
    }
}