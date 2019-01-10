package io.heapy.tgto.configuration

import java.lang.System.getenv

/**
 * @author Ruslan Ibragimov
 */
interface AppConfiguration {
    val token: String
    val name: String
    val baseUrl: String
    val ds: DataSourceConfiguration
}

class DefaultAppConfiguration(
    override val token: String = getenv("TGTO_BOT_TOKEN"),
    override val name: String = getenv("TGTO_BOT_NAME"),
    override val baseUrl: String = getenv("TGTO_BASE_URL"),
    override val ds: DataSourceConfiguration = DefaultDataSourceConfiguration()
) : AppConfiguration

interface DataSourceConfiguration {
    val url: String
    val username: String
    val password: String
    val driverClassName: String
}

class DefaultDataSourceConfiguration(
    override val url: String = getenv("TGTO_JDBC_URL"),
    override val username: String = "tgto",
    override val password: String = "tgto",
    override val driverClassName: String = "org.postgresql.Driver"
) : DataSourceConfiguration
