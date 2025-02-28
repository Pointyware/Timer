package org.pointyware.timer.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.pointyware.timer.interactors.CreateRecordUseCase
import org.pointyware.timer.interactors.CreateTaskUseCase
import org.pointyware.timer.interactors.LoadTasksUseCase

/**
 *
 */

fun coreInteractorsModule() = module {
    factoryOf(::CreateRecordUseCase)
    factoryOf(::CreateTaskUseCase)
    factoryOf(::LoadTasksUseCase)
}
