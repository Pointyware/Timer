package com.taushsampley.timer.tasks.data

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.greaterThanOrEqualTo
import org.hamcrest.Matchers.lessThanOrEqualTo
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

    @After
    @Throws(IOException::class)
    fun teardown() {
        database.close()
    }

    // region Tasks

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

    private fun setupTasks(list: List<String> = titleList) {
        runBlocking {
            list.forEach {
                taskDao.insert(TaskDto(it))
            }
        }
    }

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

    private val recordTaskTitle = "a task with records"
    private val firstTime = 10000000000L
    private fun timeSpan(offset: Long, duration: Long): Pair<Long, Long> =
        (firstTime + offset) to (firstTime + offset + duration)

    private val recordList = listOf(
        timeSpan(0, 50000L),
        timeSpan(50000L, 60000L),
        // 10000 gap
        timeSpan(120000L, 2000L),
        // 500 gap
        timeSpan(122500L, 40000L),
        timeSpan(162500L, 400L),
        timeSpan(162900L, 50000L)
    )

    private fun setupRecords(list: List<Pair<Long, Long>>, taskTitle: String): Long {
        val taskId = runBlocking {
            taskDao.insert(TaskDto(taskTitle))
        }
        runBlocking {
            recordList.forEach { record ->
                recordDao.insert(RecordDto(taskId, record.first, record.second))
            }
        }

        return taskId
    }

    // region Create

    @Test
    fun createRecord_ValidId() {
        // given an inserted task
        val taskTitle = "some task with a title"
        val taskId = runBlocking {
            taskDao.insert(TaskDto(taskTitle))
        }

        // when creating a record under that task id
        val recordId = runBlocking {
            recordDao.insert(RecordDto(taskId, 5000000L, 50000000L))
        }

        // successful insert returns non-zero id
        assertThat(recordId, not(0L))
    }

    @Test
    fun createRecord_InvalidId() {
        // given an invalid task id
        val taskTitle = "some task with a title"
        val invalidTaskId = runBlocking {
            10 + taskDao.insert(TaskDto(taskTitle))
        }

        // when creating a record under that task id, then an exception is thrown
        assertThrows(
            "Inserting record referencing invalid task throws exception",
            SQLiteConstraintException::class.java
        ) {
            runBlocking {
                val timeSpan = timeSpan(500000L, 3000L)
                recordDao.insert(RecordDto(invalidTaskId, timeSpan.first, timeSpan.second))
            }
        }
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
