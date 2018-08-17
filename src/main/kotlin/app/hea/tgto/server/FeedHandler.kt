package app.hea.tgto.server

import app.hea.tgto.coroutines.serverContext
import app.hea.tgto.dao.CUserDao
import app.hea.tgto.services.FeedBuilder
import io.undertow.util.Headers
import io.undertow.util.StatusCodes

/**
 * Provides [CoroutinesHandler].
 *
 * @author Ruslan Ibragimov
 */
interface FeedHandlerFactory {
    fun handler(): CoroutinesHandler
}

class DefaultFeedHandlerFactory(
    private val userDao: CUserDao,
    private val feedBuilder: FeedBuilder
) : FeedHandlerFactory {
    override fun handler() = CoroutinesHandler(serverContext) {
        try {
            val id = it.queryParameters["id"]?.poll()
                ?: throw NotFoundException("Please check your url.")
            val user = userDao.findByUrl(id)
                ?: throw NotFoundException("Feed with this url not found.")

            it.responseHeaders.get(Headers.CONTENT_TYPE_STRING).add("application/rss+xml")
            it.responseSender.send(feedBuilder.feed(user, 100))
        } catch (e: NotFoundException) {
            it.statusCode = StatusCodes.NOT_FOUND
            it.responseSender.send(e.message)
        }
    }

    // Yes, control flow with exceptions,
    // too lazy to use CoroutinesHandlerWrapper
    class NotFoundException(message: String) : RuntimeException(message)
}
