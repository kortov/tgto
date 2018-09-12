package app.hea.tgto.logging

import org.koin.log.Logger

/**
 * Logger adapter for Koin.
 *
 * @author Ruslan Ibragimov
 */
object Slf4jKoinLogger : Logger {
    override fun debug(msg: String) {
        LOGGER.debug(msg)
    }

    override fun err(msg: String) {
        LOGGER.error(msg)
    }

    override fun info(msg: String) {
        LOGGER.info(msg)
    }

    private val LOGGER = logger<Slf4jKoinLogger>()
}
