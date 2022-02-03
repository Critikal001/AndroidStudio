package com.example.rentmycar.data.api.request

import com.squareup.moshi.Json

data class CustomerRequest(
    @Json(name = "customerId")
    val customerId: Int,

    @Json(name = "user")
    val user: UserRequest
)
