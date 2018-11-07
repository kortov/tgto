package io.heapy.tgto.services

import io.heapy.tgto.UserInfo
import io.heapy.tgto.configuration.AppConfiguration
import io.heapy.tgto.coroutines.elastic
import io.heapy.tgto.dao.CMessageDao
import io.heapy.tgto.db.tables.pojos.Message
import io.heapy.tgto.db.tables.pojos.TgUser
import com.rometools.rome.feed.synd.SyndContentImpl
import com.rometools.rome.feed.synd.SyndEntry
import com.rometools.rome.feed.synd.SyndEntryImpl
import com.rometools.rome.feed.synd.SyndFeedImpl
import com.rometools.rome.feed.synd.SyndImageImpl
import com.rometools.rome.io.SyndFeedOutput
import java.io.StringWriter
import java.time.Instant
import java.util.Date

/**
 * Builds feed of provided user.
 *
 * @author Ruslan Ibragimov
 */
interface FeedBuilder {
    suspend fun feed(user: TgUser, limit: Int): String
}

class RomeFeedBuilder(
    private val messageDao: CMessageDao,
    private val userInfo: UserInfo,
    private val configuration: AppConfiguration,
    private val markdownService: MarkdownService
) : FeedBuilder {
    override suspend fun feed(user: TgUser, limit: Int): String = elastic {
        val messages = messageDao.list(user.userId, limit)

        val feed = SyndFeedImpl().apply {
            title = "ToRssBot"
            icon = SyndImageImpl().also {
                it.link = "${configuration.baseUrl}/favicon.ico"
            }
            link = userInfo.getFeedUrl(user)
            uri = userInfo.getFeedUrl(user)
            description = "Feed generated by ToRssBot."
            feedType = "atom_1.0"
            docs = "https://en.wikipedia.org/wiki/Atom_(Web_standard)"
            language = "en"
            generator = "ToRssBot"
            publishedDate = Date.from(Instant.now())
            entries = messages.map { toSyndEntry(user, it) }
        }

        StringWriter().use { writer ->
            val feedOutput = SyndFeedOutput()
            feedOutput.output(feed, writer)
            writer.buffer.toString()
        }
    }

    private suspend fun toSyndEntry(user: TgUser, message: Message): SyndEntry {
        return SyndEntryImpl().apply {
            title = message.message.lineSequence().first()
            link = userInfo.getFeedItemUrl(user, message)
            author = user.userId.toString()
            publishedDate = Date.from(message.created.toInstant())
            description = SyndContentImpl().also { content ->
                content.value = markdownService.render(message.message)
            }
        }
    }
}