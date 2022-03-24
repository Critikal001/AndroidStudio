package com.example.rentmycar.data.model.api.post

import com.squareup.moshi.Json

data class EngineSpec(
    @Json(name = "engineSpecId")
    val engineSpecId: Int?,

    @Json(name = "engineType")
    val engineType: EngineType,

    @Json(name = "fuelType")
    val fuelType: String,

    @Json(name = "fuelUsePerKm")
    val fuelUsePerKm: Double,

    @Json(name = "fuelPrice")
    val fuelPrice: Double,



    )
