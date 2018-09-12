package app.hea.tgto

import app.hea.tgto.commands.BotCommands
import app.hea.tgto.commands.BotCommandsWithFallback
import app.hea.tgto.commands.MyUrlCommand
import app.hea.tgto.commands.NewUrlCommand
import app.hea.tgto.commands.PingPongCommand
import app.hea.tgto.commands.SaveCommand
import app.hea.tgto.commands.StartCommand
import app.hea.tgto.configuration.AppConfiguration
import app.hea.tgto.configuration.DefaultAppConfiguration
import app.hea.tgto.dao.CMessageDao
import app.hea.tgto.dao.CUserDao
import app.hea.tgto.dao.DefaultCMessageDao
import app.hea.tgto.dao.DefaultCUserDao
import app.hea.tgto.logging.Slf4jKoinLogger
import app.hea.tgto.logging.logger
import app.hea.tgto.server.FeedServer
import app.hea.tgto.server.UndertowFeedServer
import app.hea.tgto.services.CommonMarkMarkdownService
import app.hea.tgto.services.DataSourceFactory
import app.hea.tgto.services.DatabaseMigrate
import app.hea.tgto.services.DefaultJooqConfigFactory
import app.hea.tgto.services.DefaultShutdownManager
import app.hea.tgto.services.DefaultUserInfo
import app.hea.tgto.services.FeedBuilder
import app.hea.tgto.services.FlywayDatabaseMigrate
import app.hea.tgto.services.HikariDataSourceFactory
import app.hea.tgto.services.JooqConfigFactory
import app.hea.tgto.services.MarkdownService
import app.hea.tgto.services.RomeFeedBuilder
import app.hea.tgto.services.ShutdownManager
import app.hea.tgto.services.UniquePathGenerator
import app.hea.tgto.services.UserInfo
import app.hea.tgto.services.UuidUniquePathGenerator
import app.hea.tgto.services.startup.DatabaseMigrateStartupListener
import app.hea.tgto.services.startup.DefaultStartupManager
import app.hea.tgto.services.startup.ServerStartupListener
import app.hea.tgto.services.startup.StartupManager
import app.heap.tgto.db.tables.daos.MessageDao
import app.heap.tgto.db.tables.daos.TgUserDao
import org.koin.dsl.module.module
import org.koin.experimental.builder.create
import org.koin.experimental.builder.singleBy
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

    val main = module {
        single<AppConfiguration> { DefaultAppConfiguration() }
        singleBy<ShutdownManager, DefaultShutdownManager>()
        singleBy<DataSourceFactory, HikariDataSourceFactory>()
        singleBy<DatabaseMigrate, FlywayDatabaseMigrate>()
        singleBy<JooqConfigFactory, DefaultJooqConfigFactory>()
        singleBy<UserInfo, DefaultUserInfo>()
        singleBy<UniquePathGenerator, UuidUniquePathGenerator>()
        singleBy<MarkdownService, CommonMarkMarkdownService>()
        singleBy<FeedServer, UndertowFeedServer>()
        singleBy<FeedBuilder, RomeFeedBuilder>()
        singleBy<CMessageDao, DefaultCMessageDao>()
        singleBy<CUserDao, DefaultCUserDao>()
        singleBy<ResponseChannel, DefaultResponseChannel>()
        singleBy<ReceiveChannel, ActorReceiveChannel>()
        singleBy<StartupManager, DefaultStartupManager>()
        singleBy<BotRunner, SimpleBotRunner>()

        single { create<SaveCommand>() }
        single { create<MyUrlCommand>() }
        single { create<NewUrlCommand>() }
        single { create<StartCommand>() }
        single { create<PingPongCommand>() }

        single<BotCommands> {
            BotCommandsWithFallback(
                listOf(
                    get<MyUrlCommand>(),
                    get<NewUrlCommand>(),
                    get<StartCommand>(),
                    get<PingPongCommand>()
                ),
                get<SaveCommand>()
            )
        }

        single {
            listOf(
                create<DatabaseMigrateStartupListener>(),
                create<ServerStartupListener>()
            )
        }

        single {
            TgtoBot(
                get(),
                get<ReceiveChannel>().channel,
                get<ResponseChannel>().channel,
                get()
            )
        }

        single { MessageDao(get()) }
        single { TgUserDao(get()) }
        single { get<DataSourceFactory>().dataSource() }
        single { get<JooqConfigFactory>().config() }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val koin = startKoin(listOf(main), logger = Slf4jKoinLogger)
            koin.koinContext.get<BotRunner>().run()

            LOGGER.info("Bot started.")
        } catch (e: Exception) {
            LOGGER.error("Error in bot.", e)
        }
    }

    private val LOGGER = logger<Application>()
}
