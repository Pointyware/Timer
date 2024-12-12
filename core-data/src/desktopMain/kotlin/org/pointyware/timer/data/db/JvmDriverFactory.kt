package org.pointyware.timer.data.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

/**
 *
 */
class JvmDriverFactory : DriverFactory {
    override fun createSqlDriver(path: String): SqlDriver {
        return JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY + path)
    }
}
