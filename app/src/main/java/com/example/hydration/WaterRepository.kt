package com.example.hydration

import kotlinx.coroutines.flow.Flow

class WaterRepository(private val waterDao: WaterDao) {  // Repository needs reference to data access object where the functions live to execute database queries

    suspend fun insert(record: WaterRecord) {  // defines insert function with listed parameters
        waterDao.insert(record) // calls the insert function in waterDao
    }

    suspend fun update(record: WaterRecord) { // defines update function with listed parameters
        waterDao.update(record) // calls the update function in waterDao
    }

    suspend fun delete(record: WaterRecord) {  // defines delete function with listed parameters
        waterDao.delete(record)  // calls the delete function in waterDao
    }

    fun getRecordForDay(day: String): Flow<WaterRecord> {  // defines getRecordForDay function with listed parameters that returns a Flow<WaterRecord> object
        return waterDao.getRecordForDay(day) // calls the getRecordForDay function in waterDao
    }

    fun getAllRecords(): Flow<List<WaterRecord>> {  // defines getAllRecords function.  Returns Flow<List<WaterRecord>> object
        return waterDao.getAllRecords()  // calls the getAllRecords function in waterDao
    }

}