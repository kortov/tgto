import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import Deps.jvmTarget

plugins {
    application
    kotlin("jvm").version(Deps.kotlinVersion)
}

tasks.withType<JavaCompile> {
    sourceCompatibility = jvmTarget
    targetCompatibility = jvmTarget
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = jvmTarget
}

application {
    applicationName = "tgto"
    mainClassName = "io.heapy.tgto.Application"
}

dependencies {
    implementation(Deps.kotlinStdlib)
    implementation(Deps.telegrambots)
    implementation(Deps.undertow)
    implementation(Deps.coroutines)
    implementation(Deps.rome)
    implementation(Deps.commonmark)

    implementation(Deps.komodoConcurrent)
    implementation(Deps.komodoDotenv)
    implementation(Deps.komodoLogging)
    implementation(Deps.komodoHikari)

    implementation(Deps.logback)
    implementation(Deps.julSlf4j)
    implementation(Deps.sentry)

    implementation(Deps.hikari)
    implementation(Deps.postgresql)
    implementation(Deps.jooq)
    implementation(Deps.jooqMeta)
    implementation(Deps.jooqCodegen)
    implementation(Deps.flyway)

    testImplementation(Deps.junitApi)
    testRuntimeOnly(Deps.junitEngine)
    testImplementation(Deps.mockk)
}

repositories {
    jcenter()
    maven { url = uri("https://dl.bintray.com/heapy/heap-dev") }
}
