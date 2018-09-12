package app.hea.tgto.services.startup

import app.hea.tgto.coroutines.elastic
import app.hea.tgto.services.DatabaseMigrate

/**
 * Runs migration on startup.
 *
 * @author Ruslan Ibragimov
 */
class DatabaseMigrateStartupListener(
    private val migrator: DatabaseMigrate
) : StartupListener {
    override suspend fun onStartup() = elastic {
        migrator.migrate()
    }
}
