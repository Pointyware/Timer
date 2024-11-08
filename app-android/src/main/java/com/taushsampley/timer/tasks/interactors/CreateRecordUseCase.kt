package com.taushsampley.timer.tasks.interactors

import com.taushsampley.timer.tasks.Record
import com.taushsampley.timer.tasks.Task
import com.taushsampley.timer.tasks.data.TaskRepository

/**
 */
class CreateRecordUseCase(
    private val repository: TaskRepository,
    private val createTaskUseCase: CreateTaskUseCase
) {
    suspend operator fun invoke(record: Record, task: Task) {
        repository.addRecord(record, task)
    }

    suspend operator fun invoke(record: Record, taskTitle: String): Result<Task> {
        return createTaskUseCase(taskTitle).onSuccess { task ->
            this(record, task)
        }
    }
}
