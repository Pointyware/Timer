package com.taushsampley.timer.tasks.data

import com.taushsampley.timer.tasks.Category
import com.taushsampley.timer.tasks.Node
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

    override suspend fun getRootNodes(): List<Node> {
        TODO("Not yet implemented")
    }

    override suspend fun addCategory(category: Category, parent: Category?) {
        TODO("Not yet implemented")
    }

    override suspend fun getCategoriesIn(category: Category?): List<Category> {
        TODO("Not yet implemented")
    }

    override suspend fun renameCategory(category: Category, newTitle: String) {
        TODO("Not yet implemented")
    }

    override suspend fun moveCategory(category: Category, newParent: Category?) {
        TODO("Not yet implemented")
    }

    override suspend fun removeCategory(category: Category) {
        TODO("Not yet implemented")
    }

    override suspend fun addTask(task: Task, category: Category?) {
        TODO("Not yet implemented")
    }

    override suspend fun getTasksIn(category: Category?): List<Task> {
        TODO("Not yet implemented")
    }

    override suspend fun renameTask(task: Task, newTitle: String) {
        TODO("Not yet implemented")
    }

    override suspend fun moveTask(task: Task, newCategory: Category?) {
        TODO("Not yet implemented")
    }

    override suspend fun removeTask(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun addRecord(record: Record, task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun getRecordsIn(task: Task): List<Record> {
        TODO("Not yet implemented")
    }

    override suspend fun editRecord(task: Record, start: Long, end: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun moveRecord(record: Record, task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun removeRecord(record: Record) {
        TODO("Not yet implemented")
    }
}
