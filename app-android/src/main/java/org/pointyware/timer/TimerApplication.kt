package org.pointyware.timer

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.timer.data.TaskRepository
import org.pointyware.timer.data.TaskRepositoryImpl
import org.pointyware.timer.data.db.DriverFactory
import org.pointyware.timer.data.di.sharedModule

/**
 *
 */
@HiltAndroidApp
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
