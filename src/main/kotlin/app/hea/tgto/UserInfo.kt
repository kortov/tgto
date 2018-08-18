package app.hea.tgto

import app.hea.tgto.configuration.AppConfiguration
import app.heap.tgto.db.tables.pojos.Message
import app.heap.tgto.db.tables.pojos.TgUser

/**
 * Provides info like feed url for user.
 *
 * @author Ruslan Ibragimov
 */
interface UserInfo {
    fun getFeedUrl(user: TgUser): String
    fun getFeedItemUrl(user: TgUser, message: Message): String
}

class DefaultUserInfo(
    private val appConfiguration: AppConfiguration
) : UserInfo {
    override fun getFeedUrl(user: TgUser): String {
        return "${appConfiguration.baseUrl}rss/${user.url}"
    }

    override fun getFeedItemUrl(user: TgUser, message: Message): String {
        return "${appConfiguration.baseUrl}rss/${user.url}/${message.id}"
    }
}
