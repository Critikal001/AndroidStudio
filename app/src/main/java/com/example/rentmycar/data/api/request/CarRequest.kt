package com.example.rentmycar.data.api.request

import com.squareup.moshi.Json

data class CarRequest(
    @Json(name = "registrationNr")
    val registrationNr: String,

    @Json(name = "constructionYear")
    val constructionYear: Int,

    @Json(name = "mileage")
    val mileage: Int,

    @Json(name = "model")
    val model: String,

    @Json(name = "engine")
    val engine: EngineRequest


)
