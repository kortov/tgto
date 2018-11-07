package io.heapy.tgto

import io.heapy.tgto.configuration.AppConfiguration
import io.heapy.tgto.coroutines.coExecute
import io.heapy.tgto.coroutines.serverContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

/**
 * @author Ruslan Ibragimov
 */
class TgtoBot(
    private val appConfiguration: AppConfiguration,
    private val receiveChannel: SendChannel<Update>,
    private val responseChannel: Channel<SendMessage>,
    private val shutdownManager: ShutdownManager
) : TelegramLongPollingBot() {
    override fun getBotToken() = appConfiguration.token
    override fun getBotUsername() = appConfiguration.name

    init {
        val sender = this
        GlobalScope.launch(serverContext) {
            for (message in responseChannel) {
                sender.coExecute(message)
            }
        }
    }

    override fun onUpdateReceived(update: Update) {
        if (shutdownManager.isShutdown) return

        GlobalScope.launch(serverContext) {
            receiveChannel.send(update)
        }
    }
}
