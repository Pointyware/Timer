package org.pointyware.timer.shared.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.pointyware.timer.shared.local.AndroidDriverFactory
import org.pointyware.timer.shared.local.DriverFactory

actual fun sharedPlatformModule(): Module = sharedAndroidModule()

/**
 *
 */
fun sharedAndroidModule() = module {
    singleOf(::AndroidDriverFactory) { bind<DriverFactory>()}
}
