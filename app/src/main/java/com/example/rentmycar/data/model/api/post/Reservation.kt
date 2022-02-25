package com.example.rentmycar.data.model.api.post


data class Reservation(
    val reservationNumber: String?,
    val price: Double,
    val status: String?,
    val paidAt: String?,
//    val user: User?

)
