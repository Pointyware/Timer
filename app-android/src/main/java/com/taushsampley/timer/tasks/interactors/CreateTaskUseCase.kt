package com.taushsampley.timer.tasks.interactors

import android.database.sqlite.SQLiteConstraintException
import com.taushsampley.timer.tasks.Task
import com.taushsampley.timer.tasks.data.TaskRepository

/**
 */
class CreateTaskUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(taskTitle: String): Result<Task> {
        return try {
            val task = repository.addTask(taskTitle)
            Result.success(task)
        } catch (duplicateException: SQLiteConstraintException) {
            Result.failure(duplicateException)
        }
    }
}
