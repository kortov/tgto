package app.hea.tgto

import app.hea.tgto.commands.MyUrlCommand
import app.hea.tgto.commands.NewUrlCommand
import app.hea.tgto.commands.PingPongCommand
import app.hea.tgto.commands.SaveCommand
import app.hea.tgto.commands.StartCommand
import app.hea.tgto.configuration.DefaultAppConfiguration
import app.hea.tgto.dao.DefaultCMessageDao
import app.hea.tgto.dao.DefaultCUserDao
import app.hea.tgto.logging.logger
import app.hea.tgto.server.UndertowFeedServer
import app.hea.tgto.services.CommonMarkMarkdownService
import app.hea.tgto.services.DefaultJooqConfigFactory
import app.hea.tgto.services.FlywayDatabaseMigrate
import app.hea.tgto.services.HikariDataSourceFactory
import app.hea.tgto.services.RomeFeedBuilder
import app.heap.tgto.db.tables.daos.MessageDao
import app.heap.tgto.db.tables.daos.TgUserDao
import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.meta.TelegramBotsApi

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
            val appConfiguration = DefaultAppConfiguration()
            val shutdownManager = DefaultShutdownManager()

            val dataSource = HikariDataSourceFactory(
                appConfiguration,
                shutdownManager
            ).dataSource()

            FlywayDatabaseMigrate(dataSource).migrate()

            val jooqConfig = DefaultJooqConfigFactory(dataSource).config()

            val messageDao = DefaultCMessageDao(MessageDao(jooqConfig))
            val userDao = DefaultCUserDao(TgUserDao(jooqConfig))

            val userInfo = DefaultUserInfo(appConfiguration)
            val uniquePathGenerator = UuidUniquePathGenerator()
            val markdownService = CommonMarkMarkdownService()

            val feedBuilder = RomeFeedBuilder(messageDao, userInfo, appConfiguration, markdownService)

            UndertowFeedServer(shutdownManager, userDao, feedBuilder, messageDao, markdownService).run()

            val responseChannel = DefaultResponseChannel()

            val saveCommand = SaveCommand(messageDao, responseChannel)
            val myUrlCommand = MyUrlCommand(userDao, responseChannel, userInfo)
            val newUrlCommand = NewUrlCommand(userDao, responseChannel, uniquePathGenerator, userInfo)
            val startCommand = StartCommand(userDao, responseChannel, uniquePathGenerator, userInfo)
            val pingPongCommand = PingPongCommand(responseChannel)

            val receiveChannel = ActorReceiveChannel(
                commands = listOf(myUrlCommand, newUrlCommand, startCommand, pingPongCommand),
                fallbackCommand = saveCommand
            )

            val tgBot = TgtoBot(
                appConfiguration = appConfiguration,
                receiveChannel = receiveChannel.channel,
                responseChannel = responseChannel.channel,
                shutdownManager = shutdownManager
            )

            val botSession = TelegramBotsApi().registerBot(tgBot)
            shutdownManager.onShutdown { botSession.stop() }

            LOGGER.info("Bot started.")
        } catch (e: Exception) {
            LOGGER.error("Error in bot.", e)
        }
    }

    private val LOGGER = logger<Application>()
}
