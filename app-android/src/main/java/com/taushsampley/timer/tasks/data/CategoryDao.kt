package com.taushsampley.timer.tasks.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.MapInfo
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

/**
 * Defines CRUD operations relevant to Category table.
 */
@Dao
interface CategoryDao {

    // Create
    @Insert
    suspend fun insertCategory(category: CategoryDto): Long

    // Read
    @Query("SELECT * FROM categories WHERE categories.parent = :categoryId")
    suspend fun getSubcategories(categoryId: Long?): List<CategoryDto>

    @Transaction
    @MapInfo(keyColumn = "parent")
    @Query("SELECT * FROM categories")
    suspend fun getAll(): Map<Long?, List<CategoryWithTasksAndRecords>>

    // Update
    @Update
    suspend fun update(category: CategoryDto)

    // Delete
    @Delete
    suspend fun delete(vararg category: CategoryDto)

    @Query("DELETE FROM categories WHERE categories.parent = :categoryId")
    suspend fun deleteAll(categoryId: Long?)
}
