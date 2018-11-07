package io.heapy.tgto

import kotlinx.coroutines.channels.Channel
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

/**
 * Send message to user.
 *
 * @author Ruslan Ibragimov
 */
interface ResponseChannel {
    val channel: Channel<SendMessage>
    suspend fun send(chatId: Long, message: String)
}

class DefaultResponseChannel : ResponseChannel {
    override val channel = Channel<SendMessage>(100)

    override suspend fun send(chatId: Long, message: String) {
        channel.send(SendMessage().also {
            it.chatId = chatId.toString()
            it.text = message
        })
    }
}
