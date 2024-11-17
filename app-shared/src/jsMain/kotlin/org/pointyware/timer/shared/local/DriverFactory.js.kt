package org.pointyware.timer.shared.local

import app.cash.sqldelight.db.SqlDriver

/**
 */
class JsDriverFactory: DriverFactory {
    override fun createSqlDriver(path: String): SqlDriver {
        throw UnsupportedOperationException("Not implemented")
    }
}
