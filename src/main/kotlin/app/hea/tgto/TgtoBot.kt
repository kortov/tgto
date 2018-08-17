package app.hea.tgto

import app.hea.tgto.configuration.AppConfiguration
import app.hea.tgto.coroutines.coExecute
import app.hea.tgto.coroutines.serverContext
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.SendChannel
import kotlinx.coroutines.experimental.launch
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
        launch(serverContext) {
            for (message in responseChannel) {
                sender.coExecute(message)
            }
        }
    }

    override fun onUpdateReceived(update: Update) {
        if (shutdownManager.isShutdown) return

        launch(serverContext) {
            receiveChannel.send(update)
        }
    }
}