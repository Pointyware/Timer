package org.pointyware.timer.organizer.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.pointyware.timer.organizer.viewmodels.IOrganizerViewModel
import org.pointyware.timer.organizer.viewmodels.OrganizerViewModelImpl

/**
 *
 */
fun organizerModule() = module {
    factoryOf(::OrganizerViewModelImpl) {
        bind<IOrganizerViewModel>()
    }
}
