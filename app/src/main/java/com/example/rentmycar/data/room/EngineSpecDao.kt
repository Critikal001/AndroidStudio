package com.example.rentmycar.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EngineSpecDao {
    @Query(" SELECT * FROM EngineSpecRoom WHERE id = :key ")
    suspend fun getEngineSpec(key: Int): EngineSpecRoom

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createEngineSpec(item: EngineSpecRoom): Long

    @Query(" DELETE FROM EngineSpecRoom ")
    suspend fun clear()

    @Query(" DELETE FROM EngineSpecRoom WHERE id = :key ")
    suspend fun deleteEngineSpec(key: Int)
}