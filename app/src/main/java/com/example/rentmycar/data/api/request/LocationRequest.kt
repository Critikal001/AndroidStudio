package com.example.rentmycar.data.api.request

import com.squareup.moshi.Json

data class LocationRequest(
    @Json(name = "locationId")
    val locationId: String,

    @Json(name = "city")
    val city: String,

    @Json(name = "address")
    val address: String,

    @Json(name = "longitude")
    val longitude: Double,

    @Json(name = "latitude")
    val latitude: Double
)
