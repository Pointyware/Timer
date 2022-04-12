package com.taushsampley.timer.tasks.data

import com.taushsampley.timer.tasks.Record
import com.taushsampley.timer.tasks.Task

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

    override suspend fun getTasks(): List<Task> {
        return database.taskDao.getAll().map {
            Task(it.title)
        }
    }

    override suspend fun renameTask(task: Task, newTitle: String) {
        database.taskDao.update(TaskDto(newTitle, task.id))
    }

    override suspend fun removeTask(task: Task) {
        database.taskDao.delete(TaskDto(task.title, task.id))
    }

    override suspend fun addRecord(record: Record, task: Task) {
        database.recordDao.insert(RecordDto(task.id, record.startTime, record.endTime))
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
        database.recordDao.delete(RecordDto(record.startTime, record.endTime, record.id))
    }
}
