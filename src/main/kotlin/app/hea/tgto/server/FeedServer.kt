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
        ).setCacheTime(YEAR_IN_SECONDS)

        val undertow = Undertow.builder()
            .addHttpListener(8080, "0.0.0.0", rootHandler)
            .setWorkerThreads(1) // All actual work happens in IO or on coroutines dispatcher
            .build()

        undertow.start()

        shutdownManager.onShutdown { undertow.stop() }
    }

    companion object {
        private const val YEAR_IN_SECONDS: Int = 60 * 60 * 24 * 365
    }
}
