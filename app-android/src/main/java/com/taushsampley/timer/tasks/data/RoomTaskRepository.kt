package com.taushsampley.timer.tasks.data

import org.pointyware.timer.data.TaskRepository
import org.pointyware.timer.entities.Record
import org.pointyware.timer.entities.Task

/**
 * [TaskRepository] backed by RoomDB.
 *
 * Clean Layer: IO
 */
class RoomTaskRepository(
    private val database: TaskDatabase
): TaskRepository {

    override suspend fun addTask(title: String): Task {
        val newId = database.taskDao.insert(TaskDto(title))
        return Task(title, newId)
    }

    override suspend fun getTaskByTitle(title: String): Result<Task> {
        database.taskDao.get(title)?.let {
            return Result.success(Task(it.title, it.id))
        } ?: return Result.failure(NoSuchElementException("No task with title $title"))
    }

    override suspend fun getTasks(): List<Task> {
        return database.taskDao.getAll().map {
            Task(it.title, it.id)
        }
    }

    override suspend fun renameTask(task: Task, newTitle: String) {
        database.taskDao.update(TaskDto(newTitle, task.id))
    }

    override suspend fun removeTask(task: Task) {
        database.taskDao.delete(listOf(task.title))
    }

    override suspend fun addRecord(record: Record, task: Task) = runCatching {
        val id = database.recordDao.insert(RecordDto(task.id, record.startTime, record.endTime))
        record.copy(id = id)
    }

    override suspend fun getRecordsIn(task: Task): List<Record> {
        return database.recordDao.getForTask(task.id).map {
            Record(it.start, it.end, it.id)
        }
    }

    override suspend fun editRecord(record: Record, start: Long, end: Long) {
        database.recordDao.update(RecordDto(task = 0, start = start, end = end, id = record.id))
    }

    override suspend fun moveRecord(record: Record, task: Task) {
        database.recordDao.update(RecordDto(task.id, start = record.startTime, end = record.endTime, record.id))
    }

    override suspend fun removeRecord(record: Record) {
        database.recordDao.delete(listOf(record.id))
    }
}
