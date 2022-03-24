package com.example.rentmycar.data.model.api.post

import com.squareup.moshi.Json

data class Images(
    @Json(name ="id")
    val id: Int?,
    val filePath: String
)
