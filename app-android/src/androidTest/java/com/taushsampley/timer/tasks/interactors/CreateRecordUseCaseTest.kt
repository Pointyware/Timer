package com.taushsampley.timer.tasks.interactors

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.taushsampley.timer.tasks.Record
import com.taushsampley.timer.tasks.data.RecordDao
import com.taushsampley.timer.tasks.data.RoomTaskRepository
import com.taushsampley.timer.tasks.data.TaskDao
import com.taushsampley.timer.tasks.data.TaskDatabase
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 */
@RunWith(AndroidJUnit4::class)
class CreateRecordUseCaseTest {

    private lateinit var database: TaskDatabase
    private lateinit var recordDao: RecordDao
    private lateinit var taskDao: TaskDao
    private lateinit var createTaskUseCase: CreateTaskUseCase
    private lateinit var createRecordUseCase: CreateRecordUseCase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, TaskDatabase::class.java
        ).build()
        val taskRepository = RoomTaskRepository(database)
        createTaskUseCase = CreateTaskUseCase(taskRepository)
        createRecordUseCase = CreateRecordUseCase(taskRepository, createTaskUseCase)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun createRecordByTitle() = runTest {
        // given a task title
        val taskTitle = "Task 1"
        val start = System.currentTimeMillis()
        val end = start + 5000
        val newRecord = Record(start, end)

        // when a record is created
        val result = createRecordUseCase(newRecord, taskTitle)

        // then the record is associated with the task
        assertTrue(result.isSuccess)
        assertEquals(taskTitle, result.getOrNull()?.title)
    }

    @Test
    fun createRecordByTask() {

    }
}
