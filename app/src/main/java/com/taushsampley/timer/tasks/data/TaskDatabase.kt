package com.taushsampley.timer.tasks.data

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 */
@Database(
    entities = [CategoryDto::class, TaskDto::class, RecordDto::class],
    version = 0
)
abstract class TaskDatabase: RoomDatabase() {

    abstract val categoryDao: CategoryDao

    abstract val taskDao: TaskDao

    abstract val recordDao: RecordDao

}
