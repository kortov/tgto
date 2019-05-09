package io.heapy.tgto.commands

/**
 * Represents action, can be done over telegram API
 *
 * @author Ruslan Ibragimov
 */
sealed class TgAction

data class DeleteMessageAction(
    val chatId: Long,
    val messageId: Int
) : TgAction()

data class SendMessageAction(
    val chatId: Long,
    val message: String
) : TgAction()
