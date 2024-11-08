package com.taushsampley.timer.tasks.data

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.isIn
import org.junit.After
import org.junit.Assert.assertThrows
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
                val fetchedTask = taskDao.get(title)
                assert(fetchedTask != null)
                assert(fetchedTask!!.title == title)
                assert(fetchedTask.id == taskId)
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

    private val spanList = listOf(
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
            list.forEach { span ->
                recordDao.insert(RecordDto(taskId, span.first, span.second))
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
    fun getRecordsInRange() {
        // given records inserted under a task
        val start = 100000L
        val span = 50000000L
        val mileStone = start + span / 5
        val mileStone2 = start + 2 * span / 5
        val mileStone3 = start + 3 * span / 5
        val mileStone4 = start + 4 * span / 5
        val end = start + span

        val taskTitle = "some task"
        val records = listOf(
            start to mileStone,
            mileStone to mileStone2, // in range
            mileStone2 to mileStone3, // in range
            mileStone3 to mileStone4, // in range
            mileStone4 to end
        )
        setupRecords(records, taskTitle)

        // when getting records in a time range
        val range: LongRange = mileStone2..mileStone4
        val recordsInRange = runBlocking {
            recordDao.getRange(range.first, range.last)
        }

        // then all records start or end time is in the inclusive range
        recordsInRange.forEach {
            val startInRange = range.contains(it.start)
            val endInRange = range.contains(it.end)
            assertThat(startInRange || endInRange, `is`(true))
        }

        // TODO: add second task with another range
    }

    @Test
    fun getRecordsUnderTask() {
        // given two sets of records inserted under separate tasks
        val taskId = setupRecords(spanList, recordTaskTitle)

        val shiftedSpanList = spanList.map {
            it.first + 15000L to it.second + 15000L
        }
        val shiftedTaskTitle = "$recordTaskTitle - shifted"
        val shiftedTaskId = setupRecords(shiftedSpanList, shiftedTaskTitle)

        // when retrieving records under each task
        val recordDtoList = runBlocking {
            recordDao.getForTask(taskId)
        }
        val shiftedRecordDtoList = runBlocking {
            recordDao.getForTask(shiftedTaskId)
        }

        // then the record ranges match the source lists
        fun assertCorresponds(taskId: Long, recordList: List<RecordDto>, spanList: List<Pair<Long, Long>>) {
            recordList.forEach { record ->
                assertThat(record.task, `is`(taskId))
                assertThat(record.id, not(0L))

                val correspondingSpan = spanList.find {
                    record.start == it.first && record.end == it.second
                }
                assertThat(correspondingSpan, not(nullValue()))
            }
        }
        assertCorresponds(taskId, recordDtoList, spanList)
        assertCorresponds(shiftedTaskId, shiftedRecordDtoList, shiftedSpanList)
    }

    // endregion

    // region Update

    @Test
    fun updateRecord_NewTask() {
        // given two inserted tasks with an inserted record
        val taskId = runBlocking {
            taskDao.insert(TaskDto("Some title"))
        }
        val secondTaskId = runBlocking {
            taskDao.insert(TaskDto("Another title"))
        }
        val startTime = 500000L
        val endTime = startTime + 80000L
        var recordDto = RecordDto(taskId, startTime, endTime)
        val recordId = runBlocking {
            recordDao.insert(recordDto)
        }
        recordDto = recordDto.copy(id = recordId)

        // when updating record task to new task
        val recordUpdateDto = recordDto.copy(task = secondTaskId)
        runBlocking {
            recordDao.update(recordUpdateDto)
        }

        // then record time remains unchanged and associated task is modified to new task
        val updatedRecord = runBlocking {
            recordDao.getForTask(secondTaskId).first()
        }
        assertThat(updatedRecord, `is`(recordUpdateDto))

        val oldTaskRecords = runBlocking {
            recordDao.getForTask(taskId)
        }
        assertThat("Record has been unassigned from old task",
            oldTaskRecords, `is`(emptyList())
        )
    }

    @Test
    fun updateRecord_NewSpan() {
        // given an inserted task with an inserted record
        val taskId = runBlocking {
            taskDao.insert(TaskDto("Some title"))
        }
        val startTime = 500000L
        val endTime = startTime + 80000L
        var recordDto = RecordDto(taskId, startTime, endTime)
        val recordId = runBlocking {
            recordDao.insert(recordDto)
        }
        recordDto = recordDto.copy(id = recordId)

        // when updating record span to new span
        val newStart = 234902394032L
        val newEnd = newStart + 50000000L
        val recordUpdateDto = recordDto.copy(start = newStart, end = newEnd)
        runBlocking {
            recordDao.update(recordUpdateDto)
        }

        // then record time remains unchanged and associated task is modified to new task
        val updatedRecord = runBlocking {
            recordDao.getForTask(taskId).first()
        }
        assertThat(updatedRecord, `is`(recordUpdateDto))
    }

    @Test
    fun updateRecord_InvalidTask() {
        // given an inserted task with an inserted record
        val taskId = runBlocking {
            taskDao.insert(TaskDto("Some title"))
        }
        val startTime = 500000L
        val endTime = startTime + 80000L
        var recordDto = RecordDto(taskId, startTime, endTime)
        val recordId = runBlocking {
            recordDao.insert(recordDto)
        }
        recordDto = recordDto.copy(id = recordId)

        // when updating record task to new invalid task, constraint exception is thrown
        val invalidId = 40L
        val recordUpdateDto = recordDto.copy(task = invalidId)
        assertThrows("Reassigning record's task id to invalid id, throws exception",
            SQLiteConstraintException::class.java
        ) {
            runBlocking {
                recordDao.update(recordUpdateDto)
            }
        }
    }

    // endregion

    // region Delete

    @Test
    fun removeRecord() {
        // given an inserted task and several records
        val taskId = setupRecords(spanList, recordTaskTitle)
        val recordsUnderTask = runBlocking {
            recordDao.getForTask(taskId)
        }

        // when removing subset of records
        val removedSubset = recordsUnderTask.subList(2, 5).map { it.id }
        runBlocking {
            recordDao.delete(removedSubset)
        }

        // then the retrieved records will no longer contain removed subset
        val recordsAfterRemoval = runBlocking {
            recordDao.getForTask(taskId)
        }
        assertThat(recordsAfterRemoval.size, `is`(recordsUnderTask.size - removedSubset.size))
        recordsAfterRemoval.forEach { remainingRecord ->
            assertThat(remainingRecord.id, not(isIn(removedSubset)))
        }
    }

    @Test
    fun removeAllRecordsUnderTask() {
        // given two inserted tasks with inserted records
        val taskId = setupRecords(spanList, recordTaskTitle)
        val secondSpanList = spanList
        val secondTaskId = setupRecords(secondSpanList, "$recordTaskTitle second")

        // when removing all records under task
        runBlocking {
            recordDao.deleteAll(taskId)
        }

        // then no records under task remain, but records under other tasks are unaffected
        val recordsUnderTask = runBlocking {
            recordDao.getForTask(taskId)
        }
        assertThat(recordsUnderTask, `is`(emptyList()))

        val recordsUnderSecondTask = runBlocking {
            recordDao.getForTask(secondTaskId)
        }
        assertThat(recordsUnderSecondTask.size, `is`(secondSpanList.size))
    }

    // endregion

    // endregion
}
