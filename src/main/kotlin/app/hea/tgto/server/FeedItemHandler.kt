package app.hea.tgto.server

import app.hea.tgto.coroutines.serverContext
import app.hea.tgto.dao.CMessageDao
import app.hea.tgto.dao.CUserDao
import app.hea.tgto.services.MarkdownService
import io.undertow.util.Headers
import io.undertow.util.StatusCodes

/**
 * Provides [CoroutinesHandler].
 *
 * @author Ruslan Ibragimov
 */
interface FeedItemHandlerFactory {
    fun handler(): CoroutinesHandler
}

class DefaultFeedItemHandlerFactory(
    private val userDao: CUserDao,
    private val messageDao: CMessageDao,
    private val markdownService: MarkdownService
) : FeedHandlerFactory {
    override fun handler() = CoroutinesHandler(serverContext) {
        try {
            val id = it.queryParameters["id"]?.poll()
                ?: throw NotFoundException("Please check your url.")
            val itemId = it.queryParameters["itemId"]?.poll()?.toLongOrNull()
                ?: throw NotFoundException("Please check your url.")
            val user = userDao.findByUrl(id)
                ?: throw NotFoundException("Feed with this url not found.")
            val message = messageDao.getById(itemId)
                ?: throw NotFoundException("Message with this id not found.")

            // Sort of security
            if (message.userId != user.userId) {
                throw NotFoundException("Message with this id not found.")
            }

            it.responseHeaders.add(Headers.CONTENT_TYPE, "text/html")
            it.responseSender.send("""
            <html>
            <head>
            <meta charset="UTF-8">
              <title>Message: ${message.id}</title>
            </head>
            <body>
              ${markdownService.render(message.message)}
            </body>
            </html>
            """.trimIndent())
        } catch (e: NotFoundException) {
            it.statusCode = StatusCodes.NOT_FOUND
            it.responseSender.send(e.message)
        }
    }

    // Yes, control flow with exceptions,
    // too lazy to use CoroutinesHandlerWrapper
    class NotFoundException(message: String) : RuntimeException(message)
}
