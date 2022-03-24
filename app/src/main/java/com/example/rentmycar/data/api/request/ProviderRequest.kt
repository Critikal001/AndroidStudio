package com.example.rentmycar.data.api.request

import com.squareup.moshi.Json

data class ProviderRequest(
    @Json(name = "providerId")
    val providerId: Int,

    @Json(name = "user")
    val user: UserRequest
)
