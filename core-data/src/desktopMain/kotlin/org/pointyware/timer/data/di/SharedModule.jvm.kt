package org.pointyware.timer.data.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.pointyware.timer.data.db.DriverFactory
import org.pointyware.timer.data.db.JvmDriverFactory

actual fun sharedPlatformModule(): Module = sharedJvmModule()

/**
 * Provides JVM-specific ontology dependencies
 */
fun sharedJvmModule() = module {
    singleOf(::JvmDriverFactory) { bind<DriverFactory>() }
}
