package app.hea.tgto

import app.hea.tgto.commands.Command
import app.hea.tgto.coroutines.serverContext
import app.hea.tgto.logging.logger
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.channels.SendChannel
import kotlinx.coroutines.experimental.channels.actor
import org.telegram.telegrambots.meta.api.objects.Update

/**
 * Interface for receiving messages from user.
 *
 * @author Ruslan Ibragimov
 */
interface ReceiveChannel {
    val channel: SendChannel<Update>
}

class ActorReceiveChannel(
    private val commands: List<Command>,
    private val fallbackCommand: Command
) : ReceiveChannel {
    private val _commands = commands.associate { it.name to it }

    override val channel = GlobalScope.actor<Update>(context = serverContext) {
        for (update in this.channel) {
            onReceive(update)
        }
    }

    internal suspend fun onReceive(update: Update) {
        // Updates comes with null `message`, but field `editedMessage`.
        val message = update.message ?: return
        val userId = message.from.id.toLong()

        // Accept messages only from private chat with bot
        if (message.chat.id == userId && message.text != null) {
            val command = _commands.getOrDefault(message.text, fallbackCommand)
            command.handler(update)
        }
    }

    companion object {
        private val LOGGER = logger<ActorReceiveChannel>()
    }
}
