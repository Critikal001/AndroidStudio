package com.example.rentmycar.data.model.api.post

import com.squareup.moshi.Json

data class Location(
    @Json(name = "locationId")
    val locationId: Int?,

    @Json(name = "city")
    val city: String,

    @Json(name = "address")
    val address: String,

    @Json(name = "longitude")
    val longitude: Double,

    @Json(name = "latitude")
    val latitude: Double
)
