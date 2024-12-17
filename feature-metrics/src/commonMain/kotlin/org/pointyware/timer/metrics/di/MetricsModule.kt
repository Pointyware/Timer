package org.pointyware.timer.metrics.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.pointyware.timer.metrics.interactors.GetMetricsUseCase

/**
 *
 */
fun metricsModule() = module {
    includes(
        metricsInteractorsModule()
    )
}

fun metricsInteractorsModule() = module {
    factoryOf(::GetMetricsUseCase)
}
