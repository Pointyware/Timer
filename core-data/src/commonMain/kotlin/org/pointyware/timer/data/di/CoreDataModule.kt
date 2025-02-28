package org.pointyware.timer.data.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.pointyware.timer.data.TaskRepository
import org.pointyware.timer.data.TaskRepositoryImpl
import org.pointyware.timer.data.db.Persistence

/**
 *
 */
fun coreDataModule() = module {
    includes(
        coreDataPlatformModule()
    )

    singleOf(::TaskRepositoryImpl) { bind<TaskRepository>() }
    // Make the default persistence type File
    single<Persistence> { Persistence.File }
}

expect fun coreDataPlatformModule(): Module
