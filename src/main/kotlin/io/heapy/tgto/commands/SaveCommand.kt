package io.heapy.tgto.commands

import io.heapy.tgto.ResponseChannel
import io.heapy.tgto.dao.CMessageDao
import io.heapy.tgto.db.tables.pojos.Message
import org.telegram.telegrambots.meta.api.objects.Update
import java.sql.Timestamp
import java.time.Instant

/**
 * Command that saves message to database.
 *
 * @author Ruslan Ibragimov
 */
class SaveCommand(
    private val messageDao: CMessageDao,
    private val responseChannel: ResponseChannel
) : Command {
    override val name = "/save"

    override suspend fun handler(update: Update) {
        val message = update.message
        val userId = message.from.id.toLong()

        val dbMessage = Message().also {
            it.message = message.text
            it.created = Timestamp.from(Instant.ofEpochSecond(message.date.toLong()))
            it.userId = userId
        }

        messageDao.insert(dbMessage)

        responseChannel.send(
            chatId = message.chatId,
            message = """Message "${message.text}" saved."""
        )
    }
}
