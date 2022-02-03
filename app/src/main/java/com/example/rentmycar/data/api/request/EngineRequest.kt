package com.example.rentmycar.data.api.request

import com.squareup.moshi.Json

data class EngineRequest(
    @Json(name = "motorNumber")
    val motorNumber: String,

    @Json(name = "power")
    val power: Double,

    @Json(name = "engineSpec")
    val engineSpec: EngineSpecRequest
)
