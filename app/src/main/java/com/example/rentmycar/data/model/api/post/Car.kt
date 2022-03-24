package com.example.rentmycar.data.model.api.post

import com.squareup.moshi.Json

data class Car(
    @Json(name = "registrationNr")
    val registrationNr: Int?,

    @Json(name = "constructionYear")
    val constructionYear: Int,

    @Json(name = "mileage")
    val mileage: Int,

    @Json(name = "model")
    val model: String,

    @Json(name = "engine")
    val engine: Engine?


)
