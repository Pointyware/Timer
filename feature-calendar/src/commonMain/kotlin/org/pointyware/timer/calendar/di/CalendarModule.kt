package org.pointyware.timer.calendar.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.pointyware.timer.calendar.viewmodels.CalendarViewModelImpl
import org.pointyware.timer.calendar.viewmodels.ICalendarViewModel

/**
 *
 */
fun calendarModule() = module {
    factoryOf(::CalendarViewModelImpl) {
        bind<ICalendarViewModel>()
    }
}
