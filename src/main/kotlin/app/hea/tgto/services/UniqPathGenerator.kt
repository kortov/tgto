package app.hea.tgto.services

import java.util.UUID

/**
 * Generate unique, non guessable path for serving user feeds.
 *
 * @author Ruslan Ibragimov
 */
interface UniquePathGenerator {
    fun get(): String
}

class UuidUniquePathGenerator : UniquePathGenerator {
    override fun get(): String {
        return UUID.randomUUID().toString()
    }
}
