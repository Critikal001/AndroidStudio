package com.example.rentmycar.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RentalPlanDao {
    @Query(" SELECT * FROM RentalPlan WHERE id = :key ")
    suspend fun getRentalPlan(key: Int): RentalPlan

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createRentalPlan(item: RentalPlan): Long

    @Query(" DELETE FROM RentalPlan ")
    suspend fun clear()

    @Query(" DELETE FROM RentalPlan WHERE id = :key ")
    suspend fun deleteRentalPlan(key: Int)
}