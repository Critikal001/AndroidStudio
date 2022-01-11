package com.example.rentmycar.data.model.api

import com.squareup.moshi.Json

data class User(
    @Json(name = "userName")
    val userName: String,

    @Json(name = "lastName")
    val lastName: String,

    @Json(name = "firstName")
    val firstName: String
)
