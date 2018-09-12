package app.hea.tgto

import app.hea.tgto.configuration.AppConfiguration
import app.hea.tgto.configuration.DefaultAppConfiguration
import app.hea.tgto.services.CommonMarkMarkdownService
import app.hea.tgto.services.DefaultShutdownManager
import app.hea.tgto.services.DefaultUserInfo
import app.hea.tgto.services.MarkdownService
import app.hea.tgto.services.ShutdownManager
import app.hea.tgto.services.UniquePathGenerator
import app.hea.tgto.services.UserInfo
import app.hea.tgto.services.UuidUniquePathGenerator
import app.hea.tgto.services.startup.DatabaseMigrateStartupListener
import app.hea.tgto.services.startup.DefaultStartupManager
import app.hea.tgto.services.startup.ServerStartupListener
import app.hea.tgto.services.startup.StartupManager
import org.koin.dsl.module.module
import org.koin.experimental.builder.create
import org.koin.experimental.builder.singleBy

/**
 * Different beans used in bot and server module.
 *
 * @author Ruslan Ibragimov
 */
val coreModule = module {
    single<AppConfiguration> { DefaultAppConfiguration() }
    singleBy<UserInfo, DefaultUserInfo>()
    singleBy<UniquePathGenerator, UuidUniquePathGenerator>()
    singleBy<MarkdownService, CommonMarkMarkdownService>()
    singleBy<ShutdownManager, DefaultShutdownManager>()
    singleBy<StartupManager, DefaultStartupManager>()
    single {
        listOf(
            create<DatabaseMigrateStartupListener>(),
            create<ServerStartupListener>()
        )
    }
}
