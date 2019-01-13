import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.3.11"
}

tasks.withType<JavaCompile> {
    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

val komodoVersion: String by project

val tgBotsApiVersion: String by project
val undertowVersion: String by project
val coroutinesVersion: String by project
val romeVersion: String by project
val commonmarkVersion: String by project

val logbackVersion: String by project
val slf4jVersion: String by project
val sentryVersion: String by project

val postgresqlVersion: String by project
val hikariVersion: String by project
val jooqVersion: String by project
val flywayVersion: String by project

val junitVersion: String by project
val mockkVersion: String by project

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("org.telegram:telegrambots:$tgBotsApiVersion")
    compile("io.undertow:undertow-core:$undertowVersion")
    compile("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$coroutinesVersion")
    compile("com.rometools:rome:$romeVersion")
    compile("com.atlassian.commonmark:commonmark:$commonmarkVersion")

    compile("io.heapy.komodo:komodo-core-concurrent:$komodoVersion")
    compile("io.heapy.komodo:komodo-config-dotenv:$komodoVersion")
    compile("io.heapy.komodo.integration:komodo-logging:$komodoVersion")
    compile("io.heapy.komodo.integration:komodo-datasource-hikaricp:$komodoVersion")

    compile("ch.qos.logback:logback-classic:$logbackVersion")
    compile("org.slf4j:jul-to-slf4j:$slf4jVersion")
    compile("io.sentry:sentry-logback:$sentryVersion")

    compile("com.zaxxer:HikariCP:$hikariVersion")
    compile("org.postgresql:postgresql:$postgresqlVersion")
    compile("org.jooq:jooq:$jooqVersion")
    compile("org.jooq:jooq-meta:$jooqVersion")
    compile("org.jooq:jooq-codegen:$jooqVersion")
    compile("org.flywaydb:flyway-core:$flywayVersion")

    testCompile("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testCompile("io.mockk:mockk:$mockkVersion")
}

repositories {
    jcenter()
    maven { url = uri("https://dl.bintray.com/heapy/heap-dev") }
}

application {
    applicationName = "tgto"
    mainClassName = "io.heapy.tgto.Application"
}
