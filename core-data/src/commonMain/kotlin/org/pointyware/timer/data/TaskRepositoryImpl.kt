package org.pointyware.timer.data

import org.pointyware.timer.data.db.DriverFactory
import org.pointyware.timer.data.db.Persistence
import org.pointyware.timer.data.db.TimerDatabase
import org.pointyware.timer.data.db.createOrMigrate
import org.pointyware.timer.entities.Record
import org.pointyware.timer.entities.Task

/**
 *
 */
class TaskRepositoryImpl(
    driverFactory: DriverFactory,
    persistence: Persistence = Persistence.File
): TaskRepository {

    private val driver = driverFactory.createSqlDriver(persistence)
    private val db by lazy {
        TimerDatabase.Schema.createOrMigrate(driver)
        TimerDatabase(driver)
    }

    override suspend fun addTask(title: String): Task {
        db.tasksQueries.insertTask(title)
        val lastId = db.tasksQueries.lastId().executeAsOne()
        return Task(title, lastId)
    }

    override suspend fun getTaskByTitle(title: String): Result<Task> {
        try {
            val task = db.tasksQueries.getTask(title).executeAsOne()
            return Result.success(Task(task.title, task.id))
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getTasks(): List<Task> {
        TODO("Not yet implemented")
    }

    override suspend fun renameTask(task: Task, newTitle: String) {
        TODO("Not yet implemented")
    }

    override suspend fun removeTask(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun addRecord(record: Record, task: Task): Result<Record> {
        try {
            db.tasksQueries.insertRecord(record.startTime, record.endTime, task.title)
            val lastId = db.tasksQueries.lastId().executeAsOne()
            return Result.success(record.copy(id = lastId))
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getRecordsIn(task: Task): List<Record> {
        try {
            db.tasksQueries.getRecord(task.title)
            return db.tasksQueries.getRecord(task.title).executeAsList().map {
                Record(id = it.id, startTime = it.start, endTime = it.end)
            }
        } catch (e: Exception) {
            return emptyList()
        }
    }

    override suspend fun editRecord(record: Record, start: Long, end: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun moveRecord(record: Record, task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun removeRecord(record: Record) {
        TODO("Not yet implemented")
    }
}
