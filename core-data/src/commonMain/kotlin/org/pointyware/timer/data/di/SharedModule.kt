package org.pointyware.timer.data.di

import org.koin.core.module.Module
import org.koin.dsl.module

/**
 *
 */
fun coreDataModule() = module {
    includes(
        coreDataPlatformModule()
    )
}

expect fun coreDataPlatformModule(): Module
