package com.example.rentmycar.data.model.api.post

import com.squareup.moshi.Json

data class Rental(
    @Json(name = "rentalId")
    val rentalId: Int?,

    @Json(name = "name")
    val name: String,

    @Json(name = "price")
    val price: String,

    @Json(name = "mileage")
    val mileage: Int,

    @Json(name = "inUse")
    val inUse: Boolean,

    @Json(name = "car")
    val car: Car?,

    @Json(name = "location")
    val location: Location?,

    @Json(name = "provider")
    val provider: Provider?,

    @Json(name = "images")
    val images: List<Images>?,

    @Json(name = "rentalPlan")
    val rentalPlan: RentalPlan?


)
