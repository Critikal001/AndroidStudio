package com.example.rentmycar.data.model.api.post

import com.squareup.moshi.Json

data class RunningRental(
    @Json(name = "returnLocation")
    val returnLocation: String,

    @Json(name = "drivingKm")
    val drivingKm: Int,

    @Json(name = "rental")
    val rental: Rental,

    @Json(name = "driver")
    val driver: Customer
)
