package com.taushsampley.timer.tasks.data

import androidx.room.Embedded
import androidx.room.Relation

/**
 */
data class CategoryWithTasksAndRecords(
    @Embedded
    var category: CategoryDto,
    @Relation(
        entity = TaskDto::class,
        parentColumn = CategoryDto.ID_NAME,
        entityColumn = TaskDto.CATEGORY_NAME
    )
    val tasks: List<TaskWithRecords>
)
