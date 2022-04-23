package com.example.rentmycar.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rentmycar.data.model.api.post.Engine
import com.example.rentmycar.data.model.api.post.EngineType
import com.squareup.moshi.Json
@Entity
data class CarRoom(
    @PrimaryKey
    @Json(name = "id")
    val id : Int,

    @Json(name = "constructionYear")
    val constructionYear: Int,

    @Json(name = "mileage")
    val mileage: Int,

    @Json(name = "model")
    val model: String,

    @Json(name = "power")
    val power: Double,

    @Json(name = "engineType")
    val engineType: EngineType,

    @Json(name = "fuelType")
    val fuelType: String,

    @Json(name = "fuelUsePerKm")
    val fuelUsePerKm: Double,

    @Json(name = "fuelPrice")
    val fuelPrice: Double,


    )
