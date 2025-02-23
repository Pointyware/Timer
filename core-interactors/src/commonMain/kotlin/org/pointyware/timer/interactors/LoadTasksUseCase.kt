package org.pointyware.timer.interactors

import org.pointyware.timer.data.TaskRepository

/**
 */
class LoadTasksUseCase(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke() {
        // TODO:
    }
}
