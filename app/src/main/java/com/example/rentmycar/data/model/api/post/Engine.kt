package com.example.rentmycar.data.model.api.post

import com.squareup.moshi.Json

data class Engine(
    @Json(name = "motorNumber")
    val motorNumber: String,

    @Json(name = "power")
    val power: Double,

    @Json(name = "engineSpec")
    val engineSpec: EngineSpec
)
