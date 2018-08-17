package app.hea.tgto.logging

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * @author Ruslan Ibragimov
 */
inline fun <reified T : Any> logger(): Logger = LoggerFactory.getLogger(T::class.java)