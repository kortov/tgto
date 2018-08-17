package app.hea.tgto.services

import org.jooq.Configuration
import org.jooq.impl.DefaultConfiguration
import javax.sql.DataSource

/**
 * Provides [Configuration].
 *
 * @author Ruslan Ibragimov
 */
interface JooqConfigFactory {
    fun config(): Configuration
}

class DefaultJooqConfigFactory(
    private val dataSource: DataSource
) : JooqConfigFactory {
    override fun config(): Configuration {
        return DefaultConfiguration().also {
            it.setDataSource(dataSource)
        }
    }
}
