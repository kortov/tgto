package app.hea.tgto.services

import app.hea.tgto.coroutines.elasticContext
import app.hea.tgto.logging.logger
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread

/**
 * Used for graceful shutdown resources.
 *
 * @author Ruslan Ibragimov
 */
interface ShutdownManager {
    fun onShutdown(callback: ShutdownCallback)
    val isShutdown: Boolean
}

typealias ShutdownCallback = suspend () -> Unit

class DefaultShutdownManager : ShutdownManager {
    init {
        Runtime.getRuntime().addShutdownHook(thread(start = false) {
            LOGGER.info("Stopping bot")
            runBlocking {
                callbacks.map {
                    async(elasticContext) {
                        it()
                    }
                }.forEach { it.await() }
                LOGGER.info("Bot stopped")
            }
        })
    }

    private val callbacks: MutableList<ShutdownCallback> = mutableListOf()

    override fun onShutdown(callback: ShutdownCallback) {
        callbacks.add(callback)
    }

    private val _isShutdown = AtomicBoolean(false)

    override val isShutdown = _isShutdown.get()

    companion object {
        private val LOGGER = logger<DefaultShutdownManager>()
    }
}


