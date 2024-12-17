package org.pointyware.timer.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.pointyware.timer.interactors.CreateRecordUseCase
import org.pointyware.timer.interactors.CreateTaskUseCase

/**
 *
 */

fun coreInteractorsModule() = module {
    factoryOf(::CreateRecordUseCase)
    factoryOf(::CreateTaskUseCase)
}
