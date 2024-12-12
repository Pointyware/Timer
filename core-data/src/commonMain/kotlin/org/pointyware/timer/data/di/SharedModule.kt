package org.pointyware.timer.data.di

import org.koin.core.module.Module
import org.koin.dsl.module

/**
 *
 */
fun sharedModule() = module {
    includes(
        sharedPlatformModule()
    )
}

expect fun sharedPlatformModule(): Module
