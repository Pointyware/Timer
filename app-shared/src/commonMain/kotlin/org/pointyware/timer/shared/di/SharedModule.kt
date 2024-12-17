package org.pointyware.timer.shared.di

import org.koin.dsl.module
import org.pointyware.timer.data.di.coreDataModule
import org.pointyware.timer.di.coreInteractorsModule
import org.pointyware.timer.metrics.di.metricsModule

/**
 *
 */
fun sharedModule() = module {
    includes(
        coreDataModule(),
        coreInteractorsModule(),
        metricsModule(),
    )
}
