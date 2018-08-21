import org.jetbrains.kotlin.gradle.dsl.Coroutines

plugins {
    application
    kotlin("jvm") version "1.2.61"
}

kotlin.experimental.coroutines = Coroutines.ENABLE

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
}

application {
    applicationName = "tgto"
    mainClassName = "app.hea.tgto.Application"
}
