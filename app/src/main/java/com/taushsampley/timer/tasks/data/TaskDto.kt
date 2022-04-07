package com.taushsampley.timer.tasks.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Room entity (DTO) for [com.taushsampley.timer.tasks.Task].
 */
@Entity(
    tableName = TaskDto.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = CategoryDto::class,
        parentColumns = [CategoryDto.ID_NAME],
        childColumns = [TaskDto.CATEGORY_NAME]
    )]
)
data class TaskDto(
    /**
     * Simple human-readable description of a task.
     */
    @ColumnInfo(name = TITLE_NAME)
    val title: String,

    /**
     * The category this task belongs to, or null if this task is uncategorized.
     */
    @ColumnInfo(name = CATEGORY_NAME, index = true)
    val category: Int? = null,

    /**
     * Unique row id of this task entry.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID_NAME)
    val id: Long = 0,
) {
    companion object {
        const val TABLE_NAME = "tasks"
        const val ID_NAME = "id"
        const val TITLE_NAME = "title"
        const val CATEGORY_NAME = "category"
    }
}
