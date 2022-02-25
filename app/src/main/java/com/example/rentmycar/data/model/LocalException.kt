package com.example.rentmycar.data.model

data class LocalException(
    val title: String = "",
    val description: String = ""
) : Exception()
