package app.hea.tgto.services.startup

import kotlinx.coroutines.experimental.runBlocking

/**
 * Used for running code on application startup.
 *
 * @author Ruslan Ibragimov
 * @since 1.0
 */
interface StartupManager {
    fun start()
}

class DefaultStartupManager(
    private val listeners: List<StartupListener>
) : StartupManager {
    override fun start() = runBlocking {
        listeners.forEach {
            it.onStartup()
        }
    }
}

/**
 * Listener will be executed on application startup.
 *
 * @author Ruslan Ibragimov
 * @since 1.0
 */
interface StartupListener {
    suspend fun onStartup()
}
