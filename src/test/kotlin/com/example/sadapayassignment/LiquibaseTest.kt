package com.example.sadapayassignment

import com.example.sadapayassignment.TestContainer.Companion.container
import liquibase.Liquibase
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.exception.LiquibaseException
import liquibase.resource.ClassLoaderResourceAccessor
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
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

    @Test
    fun `verify an excpetion is thrown if validate fails`() {
        val liquibase = mock(Liquibase::class.java)
        `when`(liquibase.validate()).thenThrow(LiquibaseException::class.java)
        assertThrows<LiquibaseException> { liquibase.validate() }
    }
}