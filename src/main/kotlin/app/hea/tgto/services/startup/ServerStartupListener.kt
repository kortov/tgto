package app.hea.tgto.services.startup

import app.hea.tgto.server.FeedServer

/**
 * Runs http server on start.
 *
 * @author Ruslan Ibragimov
 */
class ServerStartupListener(
    private val feedServer: FeedServer
) : StartupListener {
    override suspend fun onStartup() {
        feedServer.run()
    }
}
