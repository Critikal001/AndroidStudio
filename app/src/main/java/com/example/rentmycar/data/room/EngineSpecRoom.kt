package com.example.rentmycar.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rentmycar.data.model.api.post.EngineType
import com.squareup.moshi.Json
@Entity
data class EngineSpecRoom(
    @PrimaryKey
    @Json(name = "id")
    val id: Int,

    @Json(name = "engineType")
    val engineType: EngineType,

    @Json(name = "fuelType")
    val fuelType: String,

    @Json(name = "fuelUsePerKm")
    val fuelUsePerKm: Double,

    @Json(name = "fuelPrice")
    val fuelPrice: Double,


)
