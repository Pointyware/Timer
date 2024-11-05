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

    suspend operator fun invoke(record: Record, taskTitle: String): CreateTaskUseCase.Result {
        val result = createTaskUseCase(taskTitle)
        if (result is CreateTaskUseCase.Result.Success) {
            this(record, result.newTask)
        }
        return result
    }
}
