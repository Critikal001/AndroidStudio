package com.example.rentmycar.data.room

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Database
import androidx.room.Room
@SuppressLint("StaticFieldLeak")




    object RoomService {
        lateinit var context: Context
        val database by lazy {
            Room.databaseBuilder(context, RegisterDatabase::class.java,"dbStore")
                .allowMainThreadQueries().build()

        }
    }
