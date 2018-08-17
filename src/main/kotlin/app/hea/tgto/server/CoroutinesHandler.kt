package app.hea.tgto.server

import io.undertow.server.HttpHandler
import io.undertow.server.HttpServerExchange
import io.undertow.util.SameThreadExecutor
import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.launch
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Bridge between thread and coroutines worlds.
 *
 * @author Ruslan Ibragimov
 */
class CoroutinesHandler(
    private val context: CoroutineContext,
    private val handler: suspend (HttpServerExchange) -> Unit
) : HttpHandler {
    override fun handleRequest(exchange: HttpServerExchange) {
        exchange.dispatch(SameThreadExecutor.INSTANCE, Runnable {
            launch(
                context = context,
                start = CoroutineStart.UNDISPATCHED
            ) {
                handler(exchange)
            }
        })
    }
}
