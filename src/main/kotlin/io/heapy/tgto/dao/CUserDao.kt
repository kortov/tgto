package io.heapy.tgto.dao

import io.heapy.tgto.coroutines.elastic
import io.heapy.tgto.db.tables.TgUser.TG_USER
import io.heapy.tgto.db.tables.daos.TgUserDao
import io.heapy.tgto.db.tables.pojos.TgUser

/**
 * @author Ruslan Ibragimov
 */
interface CUserDao {
    suspend fun create(user: TgUser)
    suspend fun update(user: TgUser)
    suspend fun findByUserId(userId: Long): TgUser?
    suspend fun findByUrl(url: String): TgUser?
}

class DefaultCUserDao(
    private val userDao: TgUserDao
) : CUserDao {

    override suspend fun update(user: TgUser) = elastic {
        userDao.update(user)
    }

    override suspend fun create(user: TgUser): Unit = elastic {
        userDao.insert(user)
    }

    override suspend fun findByUserId(userId: Long): TgUser? = elastic {
        userDao.fetchOneByUserId(userId)
    }

    override suspend fun findByUrl(url: String): TgUser? = elastic {
        userDao.fetchOne(TG_USER.URL, url)
    }
}

