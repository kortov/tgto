package io.heapy.tgto.services

import io.heapy.tgto.ShutdownManager
import io.heapy.tgto.configuration.AppConfiguration
import com.zaxxer.hikari.HikariDataSource
import javax.sql.DataSource

/**
 * Provides [DataSource].
 *
 * @author Ruslan Ibragimov
 */
interface DataSourceFactory {
    fun dataSource(): DataSource
}

class HikariDataSourceFactory(
    private val appConfiguration: AppConfiguration,
    private val shutdownManager: ShutdownManager
) : DataSourceFactory {

    override fun dataSource(): DataSource {
        val dataSource = HikariDataSource().also {
            it.jdbcUrl = appConfiguration.ds.url
            it.username = appConfiguration.ds.username
            it.password = appConfiguration.ds.password
            it.driverClassName = appConfiguration.ds.driverClassName
            it.maximumPoolSize = 4
        }

        shutdownManager.onShutdown { dataSource.close() }

        return dataSource
    }
}
