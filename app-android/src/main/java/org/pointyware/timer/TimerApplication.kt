package org.pointyware.timer

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.pointyware.timer.data.di.coreDataModule

/**
 *
 */
@HiltAndroidApp
class TimerApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TimerApplication)
            modules(
                coreDataModule(),
            )
        }
    }
}
