package org.pointyware.timer.interactors

import org.pointyware.timer.data.TaskRepository
import org.pointyware.timer.entities.Task

/**
 */
class CreateTaskUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(taskTitle: String): Result<Task> {
        return try {
            val task = repository.addTask(taskTitle)
            Result.success(task)
        } catch (duplicateException: RuntimeException) {
            repository.getTaskByTitle(taskTitle)
        }
    }
}
