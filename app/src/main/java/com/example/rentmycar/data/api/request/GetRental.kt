package com.example.rentmycar.data.api.request

import com.example.rentmycar.data.model.api.post.Car
import com.example.rentmycar.data.model.api.post.Location
import com.example.rentmycar.data.model.api.post.Provider
import com.squareup.moshi.Json

data class GetRental(
    @Json(name= "rentalId")
    val rentalId: String,

    @Json(name = "name")
    val name: String,

    @Json(name = "price")
    val price: String,

    @Json(name = "mileage")
    val mileage: Int,

    @Json(name = "inUse")
    val inUse: Boolean,

    @Json(name = "car")
    val car: Car,

    @Json(name = "location")
    val location: Location,

    @Json(name = "provider")
    val provider: Provider
)
