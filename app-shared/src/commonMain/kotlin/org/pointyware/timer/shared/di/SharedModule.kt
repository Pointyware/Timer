package org.pointyware.timer.shared.di

import org.koin.dsl.module
import org.pointyware.timer.calendar.di.calendarModule
import org.pointyware.timer.data.di.coreDataModule
import org.pointyware.timer.di.coreInteractorsModule
import org.pointyware.timer.metrics.di.metricsModule
import org.pointyware.timer.organizer.di.organizerModule
import org.pointyware.timer.tasks.di.tasksModule

/**
 *
 */
fun sharedModule() = module {
    includes(
        coreDataModule(),
        coreInteractorsModule(),

        metricsModule(),
        calendarModule(),
        tasksModule(),
        organizerModule()
    )
}
