package org.pointyware.timer.data.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.pointyware.timer.data.TaskRepository
import org.pointyware.timer.data.TaskRepositoryImpl

/**
 *
 */
fun coreDataModule() = module {
    includes(
        coreDataPlatformModule()
    )

    singleOf(::TaskRepositoryImpl) { bind<TaskRepository>() }
}

expect fun coreDataPlatformModule(): Module
