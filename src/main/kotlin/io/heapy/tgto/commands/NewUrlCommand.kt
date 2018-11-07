package io.heapy.tgto.commands

import io.heapy.tgto.ResponseChannel
import io.heapy.tgto.UniquePathGenerator
import io.heapy.tgto.UserInfo
import io.heapy.tgto.dao.CUserDao
import org.telegram.telegrambots.meta.api.objects.Update

/**
 * Handles /newurl command.
 *
 * @author Ruslan Ibragimov
 */
class NewUrlCommand(
    private val userDao: CUserDao,
    private val responseChannel: ResponseChannel,
    private val uniquePathGenerator: UniquePathGenerator,
    private val userInfo: UserInfo
) : Command {
    override val name = "/newurl"

    override suspend fun handler(update: Update) {
        val user = userDao.findByUserId(update.message.from.id.toLong())
            ?: throw RuntimeException("Received command, but no user saved in database.")

        user.url = uniquePathGenerator.get()

        userDao.update(user)

        responseChannel.send(
            chatId = update.message.chatId,
            message = userInfo.getFeedUrl(user)
        )
    }
}
