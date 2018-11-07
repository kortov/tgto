package io.heapy.tgto.utils

import org.jooq.codegen.GenerationTool
import org.jooq.meta.jaxb.Configuration
import org.jooq.meta.jaxb.Database
import org.jooq.meta.jaxb.Generate
import org.jooq.meta.jaxb.Generator
import org.jooq.meta.jaxb.Jdbc
import org.jooq.meta.jaxb.Target

fun main(args: Array<String>) {
    GenerationTool.generate(Configuration().apply {
        jdbc = Jdbc().apply {
            driver = "org.postgresql.Driver"
            url = "jdbc:postgresql://localhost:5435/tgto"
            user = "tgto"
            password = "tgto"
        }

        generator = Generator().apply {
            database = Database().apply {
                name = "org.jooq.meta.postgres.PostgresDatabase"
                includes = ".*"
                inputSchema = "public"
            }

            generate = Generate().apply {
                isDaos = true
            }

            target = Target().apply {
                packageName = "io.heapy.tgto.db"
                directory = "./src/main/java"
            }
        }
    })
}
