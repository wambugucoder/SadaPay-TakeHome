package com.example.sadapayassignment

import com.example.sadapayassignment.TestContainer.Companion.container
import liquibase.Liquibase
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import org.junit.jupiter.api.Test
import org.springframework.test.context.ContextConfiguration
import java.sql.DriverManager

@ContextConfiguration(initializers = [TestContainer::class])
class LiquibaseTest {

    @Test
    fun `verify change logs are valid`() {
        // Connect to the database
        Class.forName(container.driverClassName)
        val connection = DriverManager.getConnection(container.jdbcUrl, container.username, container.password)

        // Create a Liquibase instance
        val database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(JdbcConnection(connection))
        val resourceAccessor = ClassLoaderResourceAccessor()
        val liquibase = Liquibase("db/changelog/db.changelog-master.xml", resourceAccessor, database)

        // Validate the change logs
        assertNoExceptionsEncountered {
            liquibase.validate()
        }
    }
}