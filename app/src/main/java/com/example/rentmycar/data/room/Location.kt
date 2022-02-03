package com.example.rentmycar.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
@Entity
data class Location(
    @PrimaryKey(autoGenerate = true)
    @Json(name = "id")
    val id : Int,

    @Json(name = "city")
    val city: String,

    @Json(name = "address")
    val address: String,

    @Json(name = "longitude")
    val longitude: Double,

    @Json(name = "latitude")
    val latitude: Double
)
