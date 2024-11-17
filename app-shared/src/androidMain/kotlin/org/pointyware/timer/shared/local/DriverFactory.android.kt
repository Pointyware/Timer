package org.pointyware.timer.shared.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.pointyware.timer.shared.db.TimerDatabase

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
