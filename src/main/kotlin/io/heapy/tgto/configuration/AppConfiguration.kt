package io.heapy.tgto.configuration

import java.lang.System.getenv

/**
 * Application Configuration
 *
 * @author Ruslan Ibragimov
 */
interface AppConfiguration {
    val token: String
    val name: String
    val baseUrl: String
    val ds: DataSourceConfiguration
}

/**
 * Implementation that uses env as default variables
 *
 * @author Ruslan Ibragimov
 */
class DefaultAppConfiguration(
    override val token: String = getenv("TGTO_BOT_TOKEN"),
    override val name: String = getenv("TGTO_BOT_NAME"),
    override val baseUrl: String = getenv("TGTO_BASE_URL"),
    override val ds: DataSourceConfiguration = DefaultDataSourceConfiguration()
) : AppConfiguration

/**
 * DataSource Configuration
 *
 * @author Ruslan Ibragimov
 */
interface DataSourceConfiguration {
    val url: String
    val username: String
    val password: String
    val driverClassName: String
}

/**
 * Implementation that uses env as default variables
 *
 * @author Ruslan Ibragimov
 */
class DefaultDataSourceConfiguration(
    override val url: String = getenv("TGTO_JDBC_URL"),
    override val username: String = "tgto",
    override val password: String = "tgto",
    override val driverClassName: String = "org.postgresql.Driver"
) : DataSourceConfiguration
