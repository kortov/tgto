package app.hea.tgto.server

import app.hea.tgto.DefaultShutdownManager
import app.hea.tgto.dao.CMessageDao
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
    private val feedBuilder: FeedBuilder,
    private val messageDao: CMessageDao
) : FeedServer {
    override fun run() {
        val feedHandler = DefaultFeedHandlerFactory(userDao, feedBuilder).handler()
        val feedItemHandler = DefaultFeedItemHandlerFactory(userDao, messageDao).handler()

        val undertow = Undertow.builder()
            .addHttpListener(8080, "0.0.0.0", RoutingHandler().also {
                it.get("/rss/{id}", feedHandler)
                it.get("/rss/{id}/{itemId}", feedItemHandler)
            })
            .build()

        undertow.start()

        shutdownManager.onShutdown { undertow.stop() }
    }
}
