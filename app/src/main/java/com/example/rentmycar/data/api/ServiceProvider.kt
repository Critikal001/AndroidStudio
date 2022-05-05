package com.example.rentmycar.data.api

import android.media.Image
import com.example.rentmycar.data.model.api.post.*
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


private const val BASE_URL =
    "http://10.0.2.2:8080/"
public class RestClient {
    private var retrofit: Retrofit? = null



    fun getRetrofit(): Retrofit? {
        if (retrofit == null) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClient: OkHttpClient =
                OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
        return retrofit
    }
}


interface ServiceProvider {
    //    // All getters from the api


    @GET("location/get-locationbyid/all")
    fun getLocation(): Response<List<Location>?>


    @GET("user/get-user/{userName}")
    fun getUser(@Path("userName") userName : String): Call<User>

    @GET("rental/get-rental-by-id/{rentalId}")
    fun getRentalById(@Path("rentalId") rentalId : Int): Call<Rental>

    @GET("rentalplan/get-rentalplan/{rentalplanId}")
    fun getRentalPlan(@Path("rentalplanId") rentalplanId : Int): Call<RentalPlan>

    @GET("rental/get-by-provider")
    fun getRentalByProvider(): Response<List<Rental>>

    @GET("rental/get-rental-by-car")
    fun getRentalByCar(): Response<List<Rental>>

    @GET("rental/get-rental-list")
    fun getRentalList(): Call<List<Rental>>


    //All deletes from the api
    @DELETE("car/delete-car/{id}")               
    fun deleteCar(
        @Path("id") id: Int,
    )



 //there need to come more deletes

    //All posts from the api
    
    @POST("car/create-car")
    fun createCar(
        @Body car: Car,
    ):Call<Car>

    
    @POST("car/update-car")
    fun updateCar(
        @Body car: Car,
    ): Response<Car>


    @Multipart
    @POST("rental/upload/{rentalId}")
    fun postRentalImage(@Path("rentalId") rentalId : Int,
        @Part image: MultipartBody.Part
    ): Call<Images>
    
    @POST("location/create-location")
    fun createLocation(
        @Body location: Location,
    ) :Call<Location>

    @POST("rentalplan/create-rentalplan")
    fun createRentalPlan(
        @Body rentalPlan: RentalPlan,
    ) :Call<RentalPlan>

    @POST("rental/create-rental")
    fun createRental(
        @Body rental: Rental,
    ) :Call<Rental>

    @POST("rentalplan/create-rentalplan")
    fun createRentalPlan(
        @Body rental: Rental,
    ) :Call<RentalPlan>
    
    @POST("rental/update-rental")
    fun updateRental(
        @Body rental: Rental,
    ) :Response<Rental>
    
    
    @POST("reservation/rent-car")
    fun createReservation(
        @Body reservation: Reservation,
    ) :Call<Reservation>

    @POST("runningRental/rent-car")
    fun getRental() :Response<List<Rental>>
    
    @POST("user/create-user")
    fun createUser(
        @Body user: User,
    ) : Call<User>

    @GET("rentalplan/get-timeslots")
    fun getTimeslots(): Call<List<TimeSlot>>

    @POST("rentalplan/create-selected-slot/{rentalId}")
    fun createSelectedTimeslot(@Path("rentalId") rentalId : Int,
        @Body Timeslots: List<SelectedTimeSlot>
    ):Call<SelectedTimeSlot>

    
    object RentalApi {
        val retrofitService: ServiceProvider by lazy {
            RestClient().getRetrofit()!!.create(ServiceProvider::class.java)
        }
        
    }
}