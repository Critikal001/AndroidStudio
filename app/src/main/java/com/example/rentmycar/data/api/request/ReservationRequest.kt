package com.example.rentmycar.data.api.request

import com.squareup.moshi.Json

data class ReservationRequest(
    @Json(name = "returnLocation")
    val returnLocation: Int,

    @Json(name = "drivingKm")
    val drivingKm: Int,

    @Json(name = "rental")
    val rental: RentalRequest,

    @Json(name = "driver")
    val driver: CustomerRequest
)
