package com.example.rentmycar.data.model.api

import com.squareup.moshi.Json

data class Rental(
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
