package com.example.rentmycar.data.model.api.post


data class Reservation(

    val reservationId: Int,
    val selectedSlot: SelectedTimeSlot,
    val rental: Rental,
    val user: User?,
//    val user: com.example.rentmycar.data.room.User?

)
