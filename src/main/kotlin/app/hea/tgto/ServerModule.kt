package app.hea.tgto

import app.hea.tgto.server.FeedServer
import app.hea.tgto.server.UndertowFeedServer
import app.hea.tgto.services.FeedBuilder
import app.hea.tgto.services.RomeFeedBuilder
import org.koin.dsl.module.module
import org.koin.experimental.builder.singleBy

/**
 * Contains server related beans.
 *
 * @author Ruslan Ibragimov
 */
val serverModule = module {
    singleBy<FeedServer, UndertowFeedServer>()
    singleBy<FeedBuilder, RomeFeedBuilder>()
}
