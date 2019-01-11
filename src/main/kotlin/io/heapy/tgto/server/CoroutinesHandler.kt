package io.heapy.tgto.server

import io.undertow.server.HttpHandler
import io.undertow.server.HttpServerExchange
import io.undertow.util.SameThreadExecutor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

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
            GlobalScope.launch(context = context) {
                handler(exchange)
            }
        })
    }
}
