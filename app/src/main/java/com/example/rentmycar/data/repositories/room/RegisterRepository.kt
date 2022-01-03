package com.example.rentmycar.data.repositories.room

import com.example.rentmycar.data.model.room.RegisterEntity

    class RegisterRepository(private val dao: RoomDao) {

        val users = dao.getAllUsers()

        suspend fun insert(user: RegisterEntity) {
            return dao.insert(user)
        }

        suspend fun getUserName(userName: String):RegisterEntity?{
            return dao.getUsername(userName)
        }
    }
