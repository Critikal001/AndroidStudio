package com.example.rentmycar.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rentmycar.data.model.api.post.Engine
import com.squareup.moshi.Json
@Entity
data class CarRoom(
    @PrimaryKey
    @Json(name = "id")
    val id : Int,

    @Json(name = "constructionYear")
    val constructionYear: Int,

    @Json(name = "mileage")
    val mileage: Int,

    @Json(name = "model")
    val model: String,


)
