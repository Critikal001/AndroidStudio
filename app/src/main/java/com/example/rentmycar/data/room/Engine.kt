package com.example.rentmycar.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Engine(
    @PrimaryKey
    @Json(name = "id")
    val id: Int,

    @Json(name = "power")
    val power: Double,
)
