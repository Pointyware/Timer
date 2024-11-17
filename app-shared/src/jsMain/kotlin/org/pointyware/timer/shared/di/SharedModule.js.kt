package org.pointyware.timer.shared.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.pointyware.timer.shared.local.DriverFactory
import org.pointyware.timer.shared.local.JsDriverFactory

actual fun sharedPlatformModule() = sharedJsModule()

fun sharedJsModule() = module {
    singleOf<::JsDriverFactory> { bind<DriverFactory>() }
}
