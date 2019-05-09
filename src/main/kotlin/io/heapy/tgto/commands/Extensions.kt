package io.heapy.tgto.commands

import org.telegram.telegrambots.meta.api.objects.Update

/**
 * Create delete message action,
 * by received update.
 *
 * @author Ruslan Ibragimov
 */
fun Update.deleteAction(): DeleteMessageAction {
    return DeleteMessageAction(
        message.chatId,
        message.messageId
    )
}
