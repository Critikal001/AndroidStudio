package com.example.rentmycar.data.api.request

import com.squareup.moshi.Json

data class UserRequest(
    val userId: Int,
    val userName: String,
    val lastName: String,
    val firstName: String,
    val street: String?,
    val houseNumber: String?,
    val postalCode: String?,
    val city: String?,
    val country: String?,
    val phoneNumber: String?,
    val iban: String?,
    val passWord: String,
    val userRole: Int
)
