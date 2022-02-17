package com.example.rentmycar.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CarDao {
    @Query(" SELECT * FROM CarRoom WHERE id = :key ")
    suspend fun getCar(key: Int): CarRoom

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createCar(item: CarRoom): Long

    @Query(" DELETE FROM CarRoom ")
    suspend fun clear()

    @Query(" DELETE FROM CarRoom WHERE id = :key ")
    suspend fun deleteCar(key: Int)
}