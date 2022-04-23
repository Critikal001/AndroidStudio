package com.example.rentmycar.data.model.api.post

import com.squareup.moshi.Json

data class Location(
    @Json(name = "locationId")
    val locationId: Int?,

    val street: String?,
    val houseNumber: String?,
    val postalCode: String?,
    val city: String?,
    val country: String?,
    val latitude: Double,
    val longitude: Double,
)
