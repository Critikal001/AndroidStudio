package com.example.rentmycar.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EngineDao {
    @Query(" SELECT * FROM EngineRoom WHERE id = :key ")
    suspend fun getEngine(key: Int): EngineRoom

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createEngine(item: EngineRoom): Long

    @Query(" DELETE FROM EngineRoom ")
    suspend fun clear()

    @Query(" DELETE FROM EngineRoom WHERE id = :key ")
    suspend fun deleteEngine(key: Int)
}