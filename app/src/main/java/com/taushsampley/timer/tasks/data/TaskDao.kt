package com.taushsampley.timer.tasks.data

import androidx.room.*

/**
 * Defines CRUD operations relevant to Task table.
 */
@Dao
interface TaskDao {

    // Create
    @Insert
    suspend fun insert(task: TaskDto): Long

//    // Read
//    @Transaction
//    @Query("SELECT * FROM tasks WHERE task_category = :categoryId")
//    suspend fun getWithRecords(categoryId: Long?): List<TaskWithRecords>

    @Query("SELECT * FROM tasks")
    suspend fun getAll(): List<TaskDto>

    @Query("SELECT * FROM tasks WHERE task_title = :title LIMIT 1")
    suspend fun get(title: String): TaskDto?

    // Update
    @Update
    suspend fun update(task: TaskDto)

    // Delete
    @Delete
    suspend fun delete(vararg tasks: TaskDto)

//    @Query("DELETE FROM tasks WHERE task_category = :categoryId")
//    suspend fun deleteAll(categoryId: Long?)
}
