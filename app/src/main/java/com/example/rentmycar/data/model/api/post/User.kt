package com.example.rentmycar.data.model.api.post

import com.squareup.moshi.Json

data class User(
    @Json(name = "userName")
    val userName: String,

    @Json(name = "lastName")
    val lastName: String,

    @Json(name = "firstName")
    val firstName: String,

    @Json(name = "passWord")
    val passWord: String,

    @Json(name = "userRole")
    val userRole: Int,

    val city: String?,
    val country: String?,
    val email: String?,

    val houseNumber: String?,
    val iban: String?,
    val phoneNumber: String?,
    val postalCode: String?,
    val street: String?
)
