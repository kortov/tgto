package app.hea.tgto.commands

import org.telegram.telegrambots.meta.api.objects.Update

/**
 * Every update we treat as command
 *
 * @author Ruslan Ibragimov
 */
interface Command {
    val name: String
    suspend fun handler(update: Update)
}
