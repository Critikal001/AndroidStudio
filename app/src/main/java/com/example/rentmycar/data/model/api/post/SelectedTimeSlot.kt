package com.example.rentmycar.data.model.api.post

data class SelectedTimeSlot(
    val id: Int,
    val date: String,
    val selected: Boolean,
    val timeSlot: TimeSlot?
    )
