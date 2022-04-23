package com.example.rentmycar.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RentalPlan(
    @PrimaryKey
    val id: Int?,
    val availableFrom: String,
    val availableUntil: String,

    )
