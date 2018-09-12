package app.hea.tgto.coroutines

import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.asCoroutineDispatcher
import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import kotlinx.coroutines.experimental.withContext
import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory
import java.util.concurrent.atomic.AtomicInteger

/**
 * Server context has number of threads equals to number number of processors.
 *
 * @author Ruslan Ibragimov
 */
val serverContext = newFixedThreadPoolContext(
    (Runtime.getRuntime().availableProcessors() - 1).coerceAtLeast(1),
    "Server Context"
)

private val threadFactory = object : ThreadFactory {
    private val threadNo = AtomicInteger()

    override fun newThread(runnable: Runnable): Thread {
        return Thread(runnable).also {
            it.name = "Elastic Context-${threadNo.incrementAndGet()}"
        }
    }
}

/**
 * Used for IO operations, since number of threads here unlimited.
 *
 * @author Ruslan Ibragimov
 */
val elasticContext = Executors.newCachedThreadPool(threadFactory).asCoroutineDispatcher()

/**
 * Shorthand for running code in [elasticContext].
 *
 * @author Ruslan Ibragimov
 */
suspend fun <T> elastic(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T
): T = withContext(elasticContext, start, block)
