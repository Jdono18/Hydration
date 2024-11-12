package com.example.hydration

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao // data access object
interface WaterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(record: WaterRecord)

    @Update
    suspend fun update(record: WaterRecord)

    @Delete
    suspend fun  delete(record: WaterRecord)

    @Query("SELECT * FROM WaterRecord WHERE day = :day LIMIT 1")
    fun getRecordForDay(day: String): Flow<WaterRecord>

    @Query("SELECT * FROM WaterRecord")
    fun getAllRecords(): Flow<List<WaterRecord>>

}