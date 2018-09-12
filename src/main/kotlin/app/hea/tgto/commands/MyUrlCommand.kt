package app.hea.tgto.commands

import app.hea.tgto.ResponseChannel
import app.hea.tgto.services.UserInfo
import app.hea.tgto.dao.CUserDao
import org.telegram.telegrambots.meta.api.objects.Update

/**
 * Handles /myurl command.
 *
 * @author Ruslan Ibragimov
 */
class MyUrlCommand(
    private val userDao: CUserDao,
    private val responseChannel: ResponseChannel,
    private val userInfo: UserInfo
) : Command {
    override val name = "/myurl"

    override suspend fun handler(update: Update) {
        val user = userDao.findByUserId(update.message.from.id.toLong())
            ?: throw RuntimeException("Received command, but no user saved in database.")

        responseChannel.send(
            chatId = update.message.chatId,
            message = userInfo.getFeedUrl(user)
        )
    }
}
