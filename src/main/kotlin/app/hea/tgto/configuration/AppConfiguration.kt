package app.hea.tgto.configuration

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
    override val name: String = getenv("TGTO_BOT_NAME") ?: "ToRssBot",
    override val baseUrl: String = getenv("TGTO_BASE_URL") ?: "http://localhost:8080/",
    override val ds: DataSourceConfiguration = DefaultDataSourceConfiguration()
) : AppConfiguration

interface DataSourceConfiguration {
    val url: String
    val username: String
    val password: String
    val driverClassName: String
}

class DefaultDataSourceConfiguration(
    override val url: String = byEnv(
        dev = "jdbc:postgresql://localhost:5435/tgto",
        prod = "jdbc:postgresql://tgto_database:5432/tgto"
    ),
    override val username: String = "tgto",
    override val password: String = "tgto",
    override val driverClassName: String = "org.postgresql.Driver"
) : DataSourceConfiguration

fun <T> byEnv(dev: T, prod: T): T {
    val env = getenv("SENTRY_ENVIRONMENT") ?: "dev"
    return when (env) {
        "prod" -> prod
        "dev" -> dev
        else -> throw RuntimeException("Set SENTRY_ENVIRONMENT either to 'dev' or 'prod'.")
    }
}
