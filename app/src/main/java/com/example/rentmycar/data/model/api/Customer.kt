package com.example.rentmycar.data.model.api

import com.squareup.moshi.Json

data class Customer(
    @Json(name = "user")
    val user: User
)
