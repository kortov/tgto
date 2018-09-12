package app.hea.tgto

import app.hea.tgto.commands.BotCommands
import app.hea.tgto.commands.BotCommandsWithFallback
import app.hea.tgto.commands.MyUrlCommand
import app.hea.tgto.commands.NewUrlCommand
import app.hea.tgto.commands.PingPongCommand
import app.hea.tgto.commands.SaveCommand
import app.hea.tgto.commands.StartCommand
import org.koin.dsl.module.module
import org.koin.experimental.builder.create
import org.koin.experimental.builder.singleBy

/**
 * Beans related to telegram bot itself.
 *
 * @author Ruslan Ibragimov
 */
val botModule = module {
    singleBy<ResponseChannel, DefaultResponseChannel>()
    singleBy<ReceiveChannel, ActorReceiveChannel>()

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
        TgtoBot(
            get(),
            get<ReceiveChannel>().channel,
            get<ResponseChannel>().channel,
            get()
        )
    }
}
