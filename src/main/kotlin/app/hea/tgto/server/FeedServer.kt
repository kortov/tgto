package app.hea.tgto.server

import app.hea.tgto.DefaultShutdownManager
import app.hea.tgto.dao.CUserDao
import app.hea.tgto.services.FeedBuilder
import io.undertow.Undertow
import io.undertow.server.RoutingHandler

/**
 * Server to host user feeds.
 *
 * @author Ruslan Ibragimov
 */
interface FeedServer {
    fun run()
}

class UndertowFeedServer(
    private val shutdownManager: DefaultShutdownManager,
    private val userDao: CUserDao,
    private val feedBuilder: FeedBuilder
) : FeedServer {
    override fun run() {
        val handler = DefaultFeedHandlerFactory(userDao, feedBuilder).handler()

        val undertow = Undertow.builder()
            .addHttpListener(8080, "0.0.0.0", RoutingHandler().also {
                it.get("/rss/{id}", handler)
            })
            .build()

        undertow.start()

        shutdownManager.onShutdown { undertow.stop() }
    }
}
