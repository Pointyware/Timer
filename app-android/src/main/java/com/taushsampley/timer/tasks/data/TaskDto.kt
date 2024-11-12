package com.taushsampley.timer.tasks.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Room entity (DTO) for [org.pointyware.timer.tasks.entities.Task].
 */
@Entity(
    tableName = TaskDto.TABLE_NAME,
    indices = [
        Index(
            TaskDto.TITLE_NAME,
            unique = true
        )
    ]
//    foreignKeys = [ForeignKey( TODO: reinstate later
//        entity = CategoryDto::class,
//        parentColumns = [CategoryDto.ID_NAME],
//        childColumns = [TaskDto.CATEGORY_NAME]
//    )]
)
data class TaskDto(
    /**
     * Simple human-readable description of a task.
     */
    @ColumnInfo(name = TITLE_NAME)
    val title: String,

//    /** TODO: reinstate later
//     * The category this task belongs to, or null if this task is uncategorized.
//     */
//    @ColumnInfo(name = CATEGORY_NAME, index = true)
//    val category: Int? = null,

    /**
     * Unique row id of this task entry.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID_NAME)
    val id: Long = 0,
) {
    companion object {
        const val TABLE_NAME = "tasks"
        const val ID_NAME = "task_id"
        const val TITLE_NAME = "task_title"
        const val CATEGORY_NAME = "task_category"
    }
}
