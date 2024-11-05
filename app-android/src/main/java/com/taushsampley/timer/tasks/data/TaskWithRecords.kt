package com.taushsampley.timer.tasks.data

import androidx.room.Embedded
import androidx.room.Relation

/**
 */
data class TaskWithRecords(
    @Embedded
    var task: TaskDto,
    @Relation(
        parentColumn = TaskDto.ID_NAME,
        entityColumn = RecordDto.TASK_NAME
    )
    val records: List<RecordDto>
)
