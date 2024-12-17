package org.pointyware.timer.data.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.pointyware.timer.data.db.AndroidDriverFactory
import org.pointyware.timer.data.db.DriverFactory


actual fun coreDataPlatformModule(): Module = coreDataAndroidModule()

/**
 *
 */
fun coreDataAndroidModule() = module {
    singleOf(::AndroidDriverFactory) { bind<DriverFactory>()}
}
