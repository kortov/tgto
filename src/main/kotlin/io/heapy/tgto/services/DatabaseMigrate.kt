package io.heapy.tgto.services

import org.flywaydb.core.Flyway
import javax.sql.DataSource

/**
 * Migrates database.
 *
 * @author Ruslan Ibragimov
 */
interface DatabaseMigrate {
    fun migrate()
}

class FlywayDatabaseMigrate(
    private val dataSource: DataSource
) : DatabaseMigrate {
    override fun migrate() {
        Flyway.configure()
            .dataSource(dataSource)
            .load()
            .migrate()
    }
}
