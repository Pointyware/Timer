package com.taushsampley.timer.tasks.interactors

import com.taushsampley.timer.tasks.Task
import com.taushsampley.timer.tasks.data.TaskRepository
import java.sql.SQLDataException

/**
 */
class CreateTaskUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(taskTitle: String): Result {
        return try {
            repository.addTask(taskTitle)
            Result.Success(Task(taskTitle))
        } catch (duplicateException: SQLDataException) {
            Result.Error(duplicateException)
        }
    }

    sealed interface Result {
        class Error(val throwable: Throwable): Result
        class Success(val newTask: Task): Result
    }
}
