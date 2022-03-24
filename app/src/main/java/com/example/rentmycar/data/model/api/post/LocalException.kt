package com.example.rentmycar.data.model.api.post

data class LocalException(
    val title: String = "",
    val description: String = ""
) : Exception()
