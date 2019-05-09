package io.heapy.tgto.commands

import org.telegram.telegrambots.meta.api.objects.Update

/**
 * Simple ping-ping command.
 *
 * @author Ruslan Ibragimov
 */
class PingPongCommand : Command {
    override val name = "/ping"

    override suspend fun handler(update: Update): List<TgAction> {
        return listOf(
            SendMessageAction(
                chatId = update.message.chatId,
                message = "Pong!"
            ),
            update.deleteAction()
        )
    }
}
