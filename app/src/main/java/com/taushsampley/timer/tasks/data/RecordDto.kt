package com.taushsampley.timer.tasks.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Room entity (DTO) for [com.taushsampley.timer.tasks.Record].
 */
@Entity(
    tableName = RecordDto.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = TaskDto::class,
        parentColumns = [TaskDto.ID_NAME],
        childColumns = [RecordDto.TASK_NAME]
    )]
)
data class RecordDto(
    /**
     * The row id of the task this record belongs to.
     * @see TaskDto
     */
    @ColumnInfo(name = TASK_NAME, index = true)
    val task: Long,

    /**
     * Start time of this task record.
     */
    @ColumnInfo(name = START_NAME)
    val start: Long,

    /**
     * End time of this task record.
     */
    @ColumnInfo(name = END_NAME)
    val end: Long,

    /**
     * Unique row id of this record.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID_NAME)
    val id: Long = 0,
) {
    companion object {
        const val TABLE_NAME = "records"
        const val ID_NAME = "id"
        const val TASK_NAME = "task"
        const val START_NAME = "start"
        const val END_NAME = "end"
    }
}
