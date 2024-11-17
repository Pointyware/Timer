package org.pointyware.timer.shared.interactors

import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.timer.data.TaskRepository
import org.pointyware.timer.entities.Record
import org.pointyware.timer.interactors.CreateRecordUseCase
import org.pointyware.timer.interactors.CreateTaskUseCase
import org.pointyware.timer.shared.data.TaskRepositoryImpl
import org.pointyware.timer.shared.di.sharedModule
import org.pointyware.timer.shared.local.DriverFactory
import org.pointyware.timer.shared.local.Persistence
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

/**
 */
class CreateRecordUseCaseTest {

    private lateinit var taskRepository: TaskRepository
    private lateinit var createTaskUseCase: CreateTaskUseCase
    private lateinit var createRecordUseCase: CreateRecordUseCase

    @BeforeTest
    fun setUp() {
        startKoin {
            modules(
                sharedModule(),
            )
        }
        val koin = getKoin()
        val driverFactory = koin.get<DriverFactory>()
        taskRepository = TaskRepositoryImpl(driverFactory, persistence = Persistence.InMemory)
        createTaskUseCase = CreateTaskUseCase(taskRepository)
        createRecordUseCase = CreateRecordUseCase(taskRepository, createTaskUseCase)
    }

    @AfterTest
    fun tearDown() {
        getKoin().close()
    }

    @Test
    fun createRecordByTitle() = runTest {
        // given a task title
        val taskTitle = "Task 1"
        val start = Clock.System.now().toEpochMilliseconds()
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
        val start =  Clock.System.now().toEpochMilliseconds()
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
