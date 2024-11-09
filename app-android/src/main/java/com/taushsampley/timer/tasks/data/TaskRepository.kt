package com.taushsampley.timer.tasks.data

import com.taushsampley.timer.tasks.Record
import com.taushsampley.timer.tasks.Task

/**
 * Contract definition for objects that mediate between multiple task data sources.
 *
 * I'm attempting to simplify interactions by making entities immutable and using CQRS in this
 * interface.
 *
 * Clean Layer: Interactors – implemented in outer layers
 */
interface TaskRepository {

//    suspend fun getNodes(category: Category?): List<Node>

    // region Category access
//    suspend fun addCategory(category: Category, parent: Category?)

//    suspend fun getCategoriesIn(category: Category?): List<Category>

//    suspend fun renameCategory(category: Category, newTitle: String)
//    suspend fun moveCategory(category: Category, newParent: Category?)

//    suspend fun removeCategory(category: Category)
    // endregion

    // region Task access
//    suspend fun addTask(task: Task, category: Category?)
    suspend fun addTask(title: String): Task
    suspend fun getTaskByTitle(title: String): Result<Task>

//    suspend fun getTasksIn(category: Category?): List<Task>
    suspend fun getTasks(): List<Task>

    suspend fun renameTask(task: Task, newTitle: String)
//    suspend fun moveTask(task: Task, newCategory: Category?)

    suspend fun removeTask(task: Task)
    // endregion

    // region Record access
    suspend fun addRecord(record: Record, task: Task): Result<Record>

    suspend fun getRecordsIn(task: Task): List<Record>

    suspend fun editRecord(record: Record, start: Long, end: Long)
    suspend fun moveRecord(record: Record, task: Task)

    suspend fun removeRecord(record: Record)
    // endregion
}
