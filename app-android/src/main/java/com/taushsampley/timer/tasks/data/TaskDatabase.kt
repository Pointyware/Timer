package com.taushsampley.timer.tasks.data

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 *
 */
@Database(
    entities = [
//        CategoryDto::class, TODO: reinstate after MVP complete
        TaskDto::class,
        RecordDto::class
               ],
    version = 1
)
abstract class TaskDatabase: RoomDatabase() {

//    abstract val categoryDao: CategoryDao

    abstract val taskDao: TaskDao

    abstract val recordDao: RecordDao

}
