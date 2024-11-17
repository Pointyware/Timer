package org.pointyware.timer

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.timer.data.TaskRepository
import org.pointyware.timer.shared.data.TaskRepositoryImpl
import org.pointyware.timer.shared.di.sharedModule
import org.pointyware.timer.shared.local.DriverFactory

/**
 *
 */
class TimerApplication: Application() {

    lateinit var repository: TaskRepository

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TimerApplication)
            modules(
                sharedModule(),
            )
        }
        val koin = getKoin()
        val driver = koin.get<DriverFactory>()
        repository = TaskRepositoryImpl(driver)
    }
}
