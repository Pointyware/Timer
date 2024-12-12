package org.pointyware.timer.data.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

/**
 *
 */
class AndroidDriverFactory(private val context: Context): DriverFactory {
    override fun createSqlDriver(path: String): SqlDriver {
        return if (path.isEmpty()) {
            AndroidSqliteDriver(TimerDatabase.Schema, context)
        } else {
            AndroidSqliteDriver(TimerDatabase.Schema, context, path)
        }
    }
}
