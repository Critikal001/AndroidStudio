package com.example.rentmycar.data.model.api

import com.squareup.moshi.Json

data class Provider(
    @Json(name = "user")
    val user: User
)
