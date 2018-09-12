package app.hea.tgto

import app.hea.tgto.dao.CMessageDao
import app.hea.tgto.dao.CUserDao
import app.hea.tgto.dao.DefaultCMessageDao
import app.hea.tgto.dao.DefaultCUserDao
import app.hea.tgto.services.DataSourceFactory
import app.hea.tgto.services.DatabaseMigrate
import app.hea.tgto.services.DefaultJooqConfigFactory
import app.hea.tgto.services.FlywayDatabaseMigrate
import app.hea.tgto.services.HikariDataSourceFactory
import app.hea.tgto.services.JooqConfigFactory
import app.heap.tgto.db.tables.daos.MessageDao
import app.heap.tgto.db.tables.daos.TgUserDao
import org.koin.dsl.module.module
import org.koin.experimental.builder.singleBy

/**
 * Contains database related beans.
 *
 * @author Ruslan Ibragimov
 */
val daoModule = module {
    singleBy<DataSourceFactory, HikariDataSourceFactory>()
    single { get<DataSourceFactory>().dataSource() }

    singleBy<JooqConfigFactory, DefaultJooqConfigFactory>()
    single { get<JooqConfigFactory>().config() }

    singleBy<DatabaseMigrate, FlywayDatabaseMigrate>()

    single { MessageDao(get()) }
    singleBy<CMessageDao, DefaultCMessageDao>()

    singleBy<CUserDao, DefaultCUserDao>()
    single { TgUserDao(get()) }
}
