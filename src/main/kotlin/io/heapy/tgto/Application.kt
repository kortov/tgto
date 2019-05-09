package io.heapy.tgto

import io.heapy.integration.logging.logger
import io.heapy.tgto.commands.MyUrlCommand
import io.heapy.tgto.commands.NewUrlCommand
import io.heapy.tgto.commands.PingPongCommand
import io.heapy.tgto.commands.SaveCommand
import io.heapy.tgto.commands.StartCommand
import io.heapy.tgto.configuration.DefaultAppConfiguration
import io.heapy.tgto.dao.DefaultCMessageDao
import io.heapy.tgto.dao.DefaultCUserDao
import io.heapy.tgto.db.tables.daos.MessageDao
import io.heapy.tgto.db.tables.daos.TgUserDao
import io.heapy.tgto.server.UndertowFeedServer
import io.heapy.tgto.services.CommonMarkMarkdownService
import io.heapy.tgto.services.DefaultJooqConfigFactory
import io.heapy.tgto.services.FlywayDatabaseMigrate
import io.heapy.tgto.services.HikariDataSourceFactory
import io.heapy.tgto.services.RomeFeedBuilder
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

            val saveCommand = SaveCommand(messageDao)
            val myUrlCommand = MyUrlCommand(userDao, userInfo)
            val newUrlCommand = NewUrlCommand(userDao, uniquePathGenerator, userInfo)
            val startCommand = StartCommand(userDao, uniquePathGenerator, userInfo)
            val pingPongCommand = PingPongCommand()

            val commandExecutor = DefaultCommandExecutor(
                commands = listOf(myUrlCommand, newUrlCommand, startCommand, pingPongCommand),
                fallbackCommand = saveCommand
            )

            val tgBot = TgtoBot(
                appConfiguration = appConfiguration,
                commandExecutor = commandExecutor,
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
