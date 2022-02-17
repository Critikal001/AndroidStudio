package com.example.rentmycar.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocationDao {
    @Query(" SELECT * FROM LocationRoom WHERE id = :key ")
    suspend fun getLocation(key: Int): LocationRoom

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createLocation(item: LocationRoom): Long

    @Query(" DELETE FROM LocationRoom ")
    suspend fun clear()

    @Query(" DELETE FROM LocationRoom WHERE id = :key ")
    suspend fun deleteLocation(key: Int)
}