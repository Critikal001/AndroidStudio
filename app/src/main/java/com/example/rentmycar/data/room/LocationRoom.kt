package com.example.rentmycar.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
@Entity
data class LocationRoom(
    @PrimaryKey(autoGenerate = true)
    @Json(name = "id")
    val id : Int,

    val street: String?,
    val houseNumber: String?,
    val postalCode: String?,
    val city: String?,
    val country: String?,
    val latitude: Double,
    val longitude: Double,
)
