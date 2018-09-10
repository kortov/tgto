package app.hea.tgto.dao

import app.hea.tgto.coroutines.elastic
import app.heap.tgto.db.tables.TgUser.TG_USER
import app.heap.tgto.db.tables.daos.TgUserDao
import app.heap.tgto.db.tables.pojos.TgUser

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

