package app.hea.tgto.server

import app.hea.tgto.ShutdownManager
import app.hea.tgto.dao.CMessageDao
import app.hea.tgto.dao.CUserDao
import app.hea.tgto.services.FeedBuilder
import app.hea.tgto.services.MarkdownService
import io.undertow.Undertow
import io.undertow.server.RoutingHandler
import io.undertow.server.handlers.resource.ClassPathResourceManager
import io.undertow.server.handlers.resource.ResourceHandler

/**
 * Server to host user feeds.
 *
 * @author Ruslan Ibragimov
 */
interface FeedServer {
    fun run()
}

class UndertowFeedServer(
    private val shutdownManager: ShutdownManager,
    private val userDao: CUserDao,
    private val feedBuilder: FeedBuilder,
    private val messageDao: CMessageDao,
    private val markdownService: MarkdownService
) : FeedServer {
    override fun run() {
        val feedHandler = DefaultFeedHandlerFactory(userDao, feedBuilder).handler()
        val feedItemHandler = DefaultFeedItemHandlerFactory(userDao, messageDao, markdownService).handler()

        val routingHandler = RoutingHandler().also {
            it.get("/rss/{id}", feedHandler)
            it.get("/rss/{id}/{itemId}", feedItemHandler)
        }
        val rootHandler = ResourceHandler(
            ClassPathResourceManager(this::class.java.classLoader, "public"),
            routingHandler
        )

        val undertow = Undertow.builder()
            .addHttpListener(8080, "0.0.0.0", rootHandler)
            .build()

        undertow.start()

        shutdownManager.onShutdown { undertow.stop() }
    }
}
