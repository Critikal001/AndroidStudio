package com.example.rentmycar.data.api.request

import com.example.rentmycar.data.model.api.post.*
import com.squareup.moshi.Json

data class RentalRequest(
    @Json(name= "rentalId")
    val rentalId: Int,

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
    val location: LocationRequest,

    @Json(name = "provider")
    val user: UserRequest,

    @Json(name = "images")
    val images: List<Images>?,

    @Json(name = "rentalPlan")
    val rentalPlan: RentalPlan?
)
