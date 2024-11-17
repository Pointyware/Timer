package org.pointyware.timer.shared.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.pointyware.timer.shared.local.DriverFactory
import org.pointyware.timer.shared.local.JvmDriverFactory

actual fun sharedPlatformModule(): Module = sharedJvmModule()

/**
 * Provides JVM-specific ontology dependencies
 */
fun sharedJvmModule() = module {
    singleOf(::JvmDriverFactory) { bind<DriverFactory>() }
}
