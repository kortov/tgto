package app.hea.tgto.commands

/**
 * Wrapper for DI to represent list of commands.
 *
 * @author Ruslan Ibragimov
 */
interface BotCommands {
    fun command(name: String): Command
}

/**
 * Wrapper for DI to represent list of commands.
 *
 * @author Ruslan Ibragimov
 */
class BotCommandsWithFallback(
    private val commands: List<Command>,
    private val fallbackCommand: Command
) : BotCommands {
    private val _commands = commands.associate { it.name to it }

    override fun command(name: String): Command {
        return _commands.getOrDefault(name, fallbackCommand)
    }
}
