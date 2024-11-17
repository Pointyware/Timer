package org.pointyware.timer.shared.data

import org.pointyware.timer.data.TaskRepository
import org.pointyware.timer.entities.Record
import org.pointyware.timer.entities.Task
import org.pointyware.timer.shared.db.TimerDatabase
import org.pointyware.timer.shared.local.DriverFactory
import org.pointyware.timer.shared.local.Persistence
import org.pointyware.timer.shared.local.createOrMigrate

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
        val taskDto = db.tasksQueries.getTask(title).executeAsOne()
        return Task(taskDto.title, taskDto.id)
    }

    override suspend fun getTaskByTitle(title: String): Result<Task> {
        TODO("Not yet implemented")
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
        TODO("Not yet implemented")
    }

    override suspend fun getRecordsIn(task: Task): List<Record> {
        TODO("Not yet implemented")
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
