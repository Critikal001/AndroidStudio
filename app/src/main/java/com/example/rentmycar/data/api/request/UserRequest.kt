package com.example.rentmycar.data.api.request

import com.squareup.moshi.Json

data class UserRequest(
    @Json(name = "userName")
    val userName: String,

    @Json(name = "lastName")
    val lastName: String,

    @Json(name = "firstName")
    val firstName: String
)
