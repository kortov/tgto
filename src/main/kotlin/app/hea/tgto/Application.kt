package app.hea.tgto

import app.hea.tgto.logging.Slf4jKoinLogger
import app.hea.tgto.logging.logger
import org.koin.standalone.StandAloneContext.startKoin
import org.telegram.telegrambots.ApiContextInitializer

/**
 * Entry point of bot.
 *
 * @author Ruslan Ibragimov
 */
object Application {
    init {
        // Ugh, telegram bot library
        ApiContextInitializer.init()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val koin = startKoin(listOf(
                daoModule,
                coreModule,
                serverModule,
                botModule
            ), logger = Slf4jKoinLogger)
            koin.koinContext.get<BotRunner>().run()

            LOGGER.info("Bot started.")
        } catch (e: Exception) {
            LOGGER.error("Error in bot.", e)
        }
    }

    private val LOGGER = logger<Application>()
}
