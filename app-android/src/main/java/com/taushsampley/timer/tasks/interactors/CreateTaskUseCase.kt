package com.taushsampley.timer.tasks.interactors

import com.taushsampley.timer.tasks.Task
import com.taushsampley.timer.tasks.data.TaskRepository
import java.sql.SQLDataException

/**
 */
class CreateTaskUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(taskTitle: String): Result<Task> {
        return try {
            repository.addTask(taskTitle)
            Result.success(Task(taskTitle))
        } catch (duplicateException: SQLDataException) {
            Result.failure(duplicateException)
        }
    }
}
