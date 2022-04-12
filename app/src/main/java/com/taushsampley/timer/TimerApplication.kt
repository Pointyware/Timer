package com.taushsampley.timer

import android.app.Application
import androidx.room.Room
import com.taushsampley.timer.tasks.data.RoomTaskRepository
import com.taushsampley.timer.tasks.data.TaskDatabase
import com.taushsampley.timer.tasks.data.TaskRepository

/**
 *
 */
class TimerApplication: Application() {

    private lateinit var database: TaskDatabase

    lateinit var repository: TaskRepository

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            TaskDatabase::class.java, "timer-db"
        ).build()

        repository = RoomTaskRepository(database)
    }
}
