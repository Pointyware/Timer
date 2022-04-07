package com.taushsampley.timer.tasks.data

import androidx.room.*

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
    suspend fun getAll(categoryId: Long?): List<CategoryDto>

    // Update
    @Update
    suspend fun update(category: CategoryDto)

    // Delete
    @Delete
    suspend fun delete(vararg category: CategoryDto)

    @Query("DELETE FROM categories WHERE categories.parent = :categoryId")
    suspend fun deleteAll(categoryId: Long?)
}
