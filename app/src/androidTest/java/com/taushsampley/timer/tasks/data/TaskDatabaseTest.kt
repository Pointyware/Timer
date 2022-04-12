package com.taushsampley.timer.tasks.data

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.assertThrows
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 */
@RunWith(AndroidJUnit4::class)
class TaskDatabaseTest {

    /**
     * List of unique title strings
     */
    private val titleList = listOf(
        "some title",
        "another title",
        "alpha",
        "beta",
        "science",
        "geography",
        "laundry",
        "homework",
        "programming",
        "dishes"
    )
    private val uniqueTitle = "A title not in that other list"

    private lateinit var database: TaskDatabase
    private lateinit var recordDao: RecordDao
    private lateinit var taskDao: TaskDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, TaskDatabase::class.java
        ).build()
        recordDao = database.recordDao
        taskDao = database.taskDao
    }

    private fun setupTasks(list: List<String> = titleList) {
        runBlocking {
            list.forEach {
                taskDao.insert(TaskDto(it))
            }
        }
    }

    @After
    @Throws(IOException::class)
    fun teardown() {
        database.close()
    }

    // region Tasks

    // region Create

    @Test
    fun createTask() {
        val idList = mutableListOf<Long>()

        fun validateId(newId: Long) {
            assertThat("Each insertion returns a non-zero id",
                newId, not(0L)
            )
            assertThat("Each insertion returns a unique id",
                idList, not(hasItem(newId))
            )
        }

        runBlocking {
            titleList.forEach { title ->
                val taskId = taskDao.insert(TaskDto(title))
                validateId(taskId)
                idList.add(taskId)
            }
        }
    }

    @Test
    fun createTaskDuplicate() {
        val title = titleList[0]

        runBlocking {
            taskDao.insert(TaskDto(title))
        }

        assertThrows(
            "inserting a task with the same title throws exception",
            SQLiteConstraintException::class.java
        ) {
            runBlocking {
                taskDao.insert(TaskDto(title))
            }
        }
    }

    // endregion

    // region Read

    @Test
    fun getAllTasks() {
        // Given some tasks with titles from a list are inserted in db
        setupTasks(titleList)

        // when retrieving tasks from db
        val taskList = runBlocking {
            taskDao.getAll()
        }

        // then the titles of the retrieved tasks will correspond to each of the list titles
        assertThat(taskList, not(emptyList()))
        assertThat(taskList.size, `is`(titleList.size))
        taskList.forEach { task ->
            assertThat(task.id, not(0L))
            assertThat(titleList, hasItem(task.title))
        }
    }

    @Test
    fun getTaskWithTitle_Success() {
        // Given some tasks with titles from a list are inserted in db
        setupTasks(titleList)

        val titleOfInterest = titleList[1]
        // When retrieving inserted task by title
        val task = runBlocking {
            taskDao.get(titleOfInterest)
        }
        // Then db returns complete task
        assertThat(task, not(nullValue()))
        assertThat(task?.id , not(0L))
        assertThat(task?.title, `is`(titleOfInterest))
    }

    @Test
    fun getTaskWithTitle_Fail() {
        // Given some tasks with titles from a list are inserted in db
        setupTasks(titleList)

        val titleOfInterest = uniqueTitle
        // When retrieving non-inserted task by title
        val task = runBlocking {
            taskDao.get(titleOfInterest)
        }
        // Then db returns null
        assertThat(task, nullValue())
    }

    // endregion

    // region Update

    @Test
    fun updateTaskTitle_UniqueTitle() {
        // Given some tasks with titles from a list are inserted in db, one of the resulting tasks,
        //   and a unique title
        setupTasks(titleList)
        val titleOfInterest = titleList[3]
        val taskOfInterest = runBlocking {
            taskDao.get(titleOfInterest)
        }!!
        val newTitle = uniqueTitle

        // When updating existing task title with DTO
        runBlocking {
            taskDao.update(taskOfInterest.copy(title = newTitle))
        }
        val taskWithNewTitle = runBlocking {
            taskDao.get(newTitle)
        }!!

        // Then task title is updated and id is constant
        assertThat(taskWithNewTitle.id, `is`(taskOfInterest.id))
        assertThat(taskWithNewTitle.title, `is`(newTitle))
    }

    @Test
    fun updateTaskTitle_DuplicateTitle() {
        // Given some tasks with titles from a list are inserted in db, one of the resulting tasks
        //   and an existing title
        setupTasks(titleList)
        val titleOfInterest = titleList[3]
        val taskOfInterest = runBlocking {
            taskDao.get(titleOfInterest)
        }!!
        val newTitle = titleList[5]

        // When updating title with existing title, Then exception is thrown
        assertThrows(
            "Updating with existing title throws exception",
            SQLiteConstraintException::class.java
        ) {
            runBlocking {
                taskDao.update(taskOfInterest.copy(title = newTitle))
            }
        }
    }

    // endregion

    // region Delete

    @Test
    fun removeTasks() {
        // Given some tasks with titles from a list are inserted in db, and a subset of those titles
        setupTasks(titleList)
        val subset = titleList.subList(0, 4)

        // When removing subset
        runBlocking {
            taskDao.delete(subset)
        }

        // Then database no longer contains tasks with titles in subset
        val taskList = runBlocking {
            taskDao.getAll()
        }
        assertThat(taskList.size, `is`(titleList.size - subset.size))
        taskList.forEach { task ->
            assertThat(subset, not(hasItem(task.title)))
        }
    }

    // endregion

    // endregion

    // region Records

    // region Create

    @Test
    fun createRecord() {
        fail("Not yet implemented")
    }

    @Test
    fun createRecordUnderTask() {
        fail("Not yet implemented")
    }

    // endregion

    // region Read

    @Test
    fun getAllRecords() {
        fail("Not yet implemented")
    }

    @Test
    fun getRecordsInRange() {
        fail("Not yet implemented")
    }

    @Test
    fun getRecordsUnderTask() {
        fail("Not yet implemented")
    }

    // endregion

    // region Update



    // endregion

    // region Delete

    @Test
    fun removeRecord() {
        fail("Not yet implemented")
    }

    @Test
    fun removeAllRecordsUnderTask() {
        fail("Not yet implemented")
    }

    // endregion

    // endregion
}
