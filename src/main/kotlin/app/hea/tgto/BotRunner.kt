package app.hea.tgto

import app.hea.tgto.services.ShutdownManager
import app.hea.tgto.services.startup.StartupManager
import org.telegram.telegrambots.meta.TelegramBotsApi

/**
 * Starts bot.
 *
 * @author Ruslan Ibragimov
 */
interface BotRunner {
    fun run()
}

/**
 * Starts bot.
 *
 * @author Ruslan Ibragimov
 */
class SimpleBotRunner(
    private val tgtoBot: TgtoBot,
    private val shutdownManager: ShutdownManager,
    private val startupManager: StartupManager
) : BotRunner {
    override fun run() {
        startupManager.start()
        val botSession = TelegramBotsApi().registerBot(tgtoBot)
        shutdownManager.onShutdown { botSession.stop() }
    }
}
