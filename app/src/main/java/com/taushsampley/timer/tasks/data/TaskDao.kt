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

    // Read
    @Query("SELECT * FROM tasks WHERE task_category = :categoryId")
    suspend fun getByCategory(categoryId: Long?): List<TaskDto>

    // Update
    @Update
    suspend fun update(task: TaskDto)

    // Delete
    @Delete
    suspend fun delete(vararg tasks: TaskDto)

    @Query("DELETE FROM tasks WHERE task_category = :categoryId")
    suspend fun deleteAll(categoryId: Long?)
}
