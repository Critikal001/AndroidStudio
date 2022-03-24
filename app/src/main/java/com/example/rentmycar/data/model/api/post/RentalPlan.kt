package com.example.rentmycar.data.model.api.post

data class RentalPlan(
    val id: Int?,
    val availableFrom: String,
    val availableUntil: String,
    val price: Double,
    val userId: Int,
    val car: Car?,
    val createdAt: String,
    val updatedAt: String?
)