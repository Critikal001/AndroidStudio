package com.example.rentmycar.data.repositories.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.rentmycar.data.model.room.RegisterEntity

@Dao
interface RoomDao {
    @Insert
     fun insert(register: RegisterEntity)

    @Query("SELECT * FROM Register_users_table ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<RegisterEntity>>

    @Query("SELECT * FROM Register_users_table WHERE user_name LIKE :userName")
     fun getUsername(userName: String): RegisterEntity?
}