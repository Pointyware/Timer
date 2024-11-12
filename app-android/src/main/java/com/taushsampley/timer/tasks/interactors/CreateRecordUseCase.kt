package com.taushsampley.timer.tasks.interactors

import com.taushsampley.timer.tasks.Record
import com.taushsampley.timer.tasks.data.TaskRepository
import org.pointyware.timer.tasks.entities.Task

/**
 */
class CreateRecordUseCase(
    private val repository: TaskRepository,
    private val createTaskUseCase: CreateTaskUseCase
) {
    suspend operator fun invoke(record: Record, task: Task): Result<Record> {
        return repository.addRecord(record, task)
    }

    suspend operator fun invoke(record: Record, taskTitle: String): Result<Record> {
        return createTaskUseCase(taskTitle).mapCatching {
            this(record, it).getOrThrow()
        }
    }
}
