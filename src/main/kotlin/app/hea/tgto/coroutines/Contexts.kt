package app.hea.tgto.coroutines

import io.heapy.komodo.core.concurent.KomodoThreadFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

/**
 * Server context has number of threads equals to number number of processors.
 *
 * @author Ruslan Ibragimov
 */
val serverContext = Executors.newFixedThreadPool(
    Runtime.getRuntime().availableProcessors() - 1,
    KomodoThreadFactory(nameProducer = { "tgto-server-$it" })
).asCoroutineDispatcher()

/**
 * Shorthand for running code in [Dispatchers.IO].
 *
 * @author Ruslan Ibragimov
 */
suspend fun <T> elastic(
    block: suspend CoroutineScope.() -> T
): T = withContext(Dispatchers.IO, block)
