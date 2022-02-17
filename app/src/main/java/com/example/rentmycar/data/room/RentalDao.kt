package com.example.rentmycar.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RentalDao {
    @Query(" SELECT * FROM RentalRoom WHERE id = :key ")
    suspend fun getRental(key: Int): RentalRoom

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createRental(item: RentalRoom): Long

    @Query(" DELETE FROM RentalRoom ")
    suspend fun clear()

    @Query(" DELETE FROM RentalRoom WHERE id = :key ")
    suspend fun deleteRental(key: Int)
}