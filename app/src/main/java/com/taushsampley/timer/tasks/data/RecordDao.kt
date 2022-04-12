package com.taushsampley.timer.tasks.data

import androidx.room.*

/**
 * Defines CRUD operations relevant to Record table.
 */
@Dao
interface RecordDao {

    // Create
    @Insert
    suspend fun insert(record: RecordDto): Long

    // Read
    @Query("SELECT * FROM records WHERE record_task = :taskId ORDER BY records.start")
    suspend fun getForTask(taskId: Long): List<RecordDto>

    @Query("SELECT * FROM records WHERE " +
            "start BETWEEN :start AND :end OR " +
            "`end` BETWEEN :start AND :end")
    suspend fun getRange(start: Long, end: Long): List<RecordDto>

    // Update
    @Update
    suspend fun update(record: RecordDto)

    // Delete
    @Delete
    suspend fun delete(vararg records: RecordDto)

    @Query("DELETE FROM records WHERE record_task = :taskId")
    suspend fun deleteAll(taskId: Long)
}
