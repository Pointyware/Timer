package com.taushsampley.timer.tasks.interactors

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext.startKoin
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.timer.data.TaskRepository
import org.pointyware.timer.entities.Record
import org.pointyware.timer.shared.data.TaskRepositoryImpl
import org.pointyware.timer.shared.local.DriverFactory
import org.pointyware.timer.shared.local.Persistence

/**
 */
@RunWith(AndroidJUnit4::class)
class CreateRecordUseCaseTest {

    private lateinit var taskRepository: TaskRepository
    private lateinit var createTaskUseCase: CreateTaskUseCase
    private lateinit var createRecordUseCase: CreateRecordUseCase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        startKoin {
            modules(

            )
        }
        val koin = getKoin()
        val driverFactory = koin.get<DriverFactory>()
        taskRepository = TaskRepositoryImpl(driverFactory, persistence = Persistence.InMemory)
        createTaskUseCase = CreateTaskUseCase(taskRepository)
        createRecordUseCase = CreateRecordUseCase(taskRepository, createTaskUseCase)
    }

    @After
    fun tearDown() {
        getKoin().close()
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
        val task = result.getOrThrow()
        assertEquals(start, task.startTime)
        assertEquals(end, task.endTime)
        assertNotEquals(0, task.id)
    }

    @Test
    fun createRecordByTask() = runTest {
        // given a task
        val task = createTaskUseCase("Task 1").getOrNull()!!
        val start = System.currentTimeMillis()
        val end = start + 5000
        val newRecord = Record(start, end)

        // when a record is created
        createRecordUseCase(newRecord, task)
        val record = taskRepository.getRecordsIn(task).first()

        // then the first record in the task is the new record
        assertEquals(start, record.startTime)
        assertEquals(end, record.endTime)
        assertNotEquals(0, record.id)
    }
}
