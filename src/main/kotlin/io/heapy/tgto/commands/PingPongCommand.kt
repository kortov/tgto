package io.heapy.tgto.commands

import io.heapy.tgto.ResponseChannel
import org.telegram.telegrambots.meta.api.objects.Update

/**
 * Simple ping-ping command.
 *
 * @author Ruslan Ibragimov
 */
class PingPongCommand(
    private val responseChannel: ResponseChannel
) : Command {
    override val name = "/ping"

    override suspend fun handler(update: Update) {
        responseChannel.send(
            chatId = update.message.chatId,
            message = "Pong!"
        )
    }
}
