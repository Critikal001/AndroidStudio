package com.example.rentmycar.data.room

import android.app.Application

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        RoomService.context=applicationContext
    }
}