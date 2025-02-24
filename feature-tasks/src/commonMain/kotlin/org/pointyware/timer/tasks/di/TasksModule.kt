package org.pointyware.timer.tasks.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.pointyware.timer.tasks.viewmodels.ITimerViewModel
import org.pointyware.timer.tasks.viewmodels.TimerViewModelImpl

/**
 *
 */
fun tasksModule() = module {

    factoryOf(::TimerViewModelImpl) {
        bind<ITimerViewModel>()
    }
}
