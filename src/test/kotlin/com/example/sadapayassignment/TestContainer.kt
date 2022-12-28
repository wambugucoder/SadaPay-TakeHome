package com.example.sadapayassignment

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.env.PropertiesPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.util.*

@Testcontainers
class TestContainer : ApplicationContextInitializer<ConfigurableApplicationContext> {

    companion object {

        @Container
        val container = PostgreSQLContainer<Nothing>("postgres:11.5").apply {
            withDatabaseName("testDb")
            withUsername("test")
            withPassword("test")
            start()
        }
    }

    override fun initialize(applicationContext: ConfigurableApplicationContext) {

        val properties = Properties()
        properties["spring.liquibase.change-log"] = "classpath:/db/changelog/db.changelog-master.xml"
        properties["spring.datasource.url"] = container.jdbcUrl
        properties["spring.datasource.username"] = container.username
        properties["spring.datasource.password"] = container.password
        properties["spring.jpa.hibernate.ddl-auto"] = "none"
        properties["spring.datasource.initialization-mode"] = "always"
        properties["spring.datasource.platform"] = "postgres"
        properties["spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation"] = "true"
        properties["spring.datasource.driver-class-name"] = "org.postgresql.Driver"
        properties["spring.jpa.database-platform"] = "org.hibernate.dialect.PostgreSQLDialect"
        properties["spring.jpa.properties.hibernate.enable_lazy_load_no_trans"] = "true"
        applicationContext.environment.propertySources.addLast(PropertiesPropertySource("testcontainers", properties))
    }
}