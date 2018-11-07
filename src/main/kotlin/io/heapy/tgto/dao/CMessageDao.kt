package io.heapy.tgto.dao

import io.heapy.tgto.coroutines.elastic
import io.heapy.tgto.db.tables.Message.MESSAGE
import io.heapy.tgto.db.tables.daos.MessageDao
import io.heapy.tgto.db.tables.pojos.Message
import org.jooq.impl.DSL

/**
 * @author Ruslan Ibragimov
 */
interface CMessageDao {
    suspend fun insert(message: Message)
    suspend fun getById(id: Long): Message?
    suspend fun list(userId: Long, limit: Int): List<Message>
}

class DefaultCMessageDao constructor(
    private val messageDao: MessageDao
) : CMessageDao {

    override suspend fun insert(message: Message) = elastic {
        messageDao.insert(message)
    }

    override suspend fun getById(id: Long): Message? = elastic {
        messageDao.fetchOneById(id)
    }

    override suspend fun list(userId: Long, limit: Int): List<Message> = elastic {
        DSL.using(messageDao.configuration())
            .selectFrom(MESSAGE)
            .where(MESSAGE.USER_ID.equal(userId))
            .limit(limit)
            .fetch()
            .map(messageDao.mapper())
    }
}
