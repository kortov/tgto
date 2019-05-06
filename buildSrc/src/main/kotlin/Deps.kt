object Deps {
    const val kotlinVersion = "1.3.31"
    const val jvmTarget = "11"

    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"

    private const val komodoVersion = "0.0.1-dev-b39"
    val komodoConcurrent = "io.heapy.komodo:komodo-core-concurrent:$komodoVersion"
    val komodoDotenv = "io.heapy.komodo:komodo-config-dotenv:$komodoVersion"
    val komodoLogging = "io.heapy.komodo.integration:komodo-logging:$komodoVersion"
    val komodoHikari = "io.heapy.komodo.integration:komodo-datasource-hikaricp:$komodoVersion"

    const val telegrambots = "org.telegram:telegrambots:4.2"
    const val undertow = "io.undertow:undertow-core:2.0.20.Final"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.2.1"
    const val rome = "com.rometools:rome:1.12.0"
    const val commonmark = "com.atlassian.commonmark:commonmark:0.12.1"

    const val logback = "ch.qos.logback:logback-classic:1.3.0-alpha4"
    const val julSlf4j = "org.slf4j:jul-to-slf4j:1.8.0-beta4"
    const val sentry = "io.sentry:sentry-logback:1.7.22"

    const val postgresql = "org.postgresql:postgresql:42.2.5"
    const val hikari = "com.zaxxer:HikariCP:3.3.1"
    const val flyway = "org.flywaydb:flyway-core:5.2.4"

    private const val jooqVersion = "3.11.11"
    const val jooq = "org.jooq:jooq:$jooqVersion"
    const val jooqMeta = "org.jooq:jooq-meta:$jooqVersion"
    const val jooqCodegen = "org.jooq:jooq-codegen:$jooqVersion"

    private const val junitVersion = "5.4.2"
    const val junitApi = "org.junit.jupiter:junit-jupiter-api:$junitVersion"
    const val junitEngine = "org.junit.jupiter:junit-jupiter-engine:$junitVersion"
    const val mockk = "io.mockk:mockk:1.9.3"
}
