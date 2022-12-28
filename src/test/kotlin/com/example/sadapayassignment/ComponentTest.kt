package com.example.sadapayassignment

import com.example.sadapayassignment.Fixtures.MODEL_PACKAGE
import com.example.sadapayassignment.Fixtures.REPO_PACKAGE
import com.example.sadapayassignment.Fixtures.getClassesInPackage
import com.example.sadapayassignment.utils.containsAllSpecifiedElements
import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.v3.oas.models.OpenAPI
import liquibase.Liquibase
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import org.assertj.core.api.AssertionsForInterfaceTypes.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.sql.DriverManager
import javax.sql.DataSource

@AutoConfigureMockMvc
internal class ComponentTest : BaseTest() {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @Autowired
    private lateinit var dataSource: DataSource

    @Test
    fun `test that Swagger works correctly`() {
        val apiDocsResult = mockMvc.perform(get("/v3/api-docs"))
            .andExpect(status().isOk)
            .andReturn().response.contentAsString

        val apiDocs = ObjectMapper().readValue(apiDocsResult, OpenAPI::class.java)
        assertThat(apiDocs.info.title).isEqualTo("SadaPay Test API")
        assertThat(apiDocs.info.version).isEqualTo("1.0")
    }

    @Test
    fun `test that all liquibase scripts are configured correctly`() {
        // Connect to the database
        Class.forName(TestContainer.container.driverClassName)
        val connection = DriverManager.getConnection(
            TestContainer.container.jdbcUrl,
            TestContainer.container.username,
            TestContainer.container.password
        )

        // Create a Liquibase instance
        val database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(JdbcConnection(connection))
        val resourceAccessor = ClassLoaderResourceAccessor()
        val liquibase = Liquibase("db/changelog/db.changelog-master.xml", resourceAccessor, database)

        // Validate the change logs
        assertNoExceptionsEncountered {
            liquibase.validate()
        }
    }

    @ParameterizedTest
    @ValueSource(strings = [REPO_PACKAGE, MODEL_PACKAGE])
    fun `test that all files under specific packages are annotated as required`(packageName: String) {
        val retrievedClasses = getClassesInPackage(packageName)
        if (packageName == REPO_PACKAGE) {
            retrievedClasses
                .filter { !it.isAnnotationPresent(Repository::class.java) }
                .forEach { fail("Interface ${it.name} is not annotated with @Repository") }
        } else {
            retrievedClasses
                .filter { !it.isAnnotationPresent(javax.persistence.Entity::class.java) }
                .forEach { fail("Class ${it.name} is not annotated with @Entity") }
        }
    }

    @Test
    fun `test that the postgres connection is up and is the default DB for tests`() {
        val result = jdbcTemplate.queryForObject("SELECT 1", Int::class.java)
        val connection = dataSource.connection
        val metaData = connection.metaData
        assertThat(metaData.databaseProductName).isEqualTo("PostgreSQL")
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `test to confirm the only active profile on a normal startup`() {
        val context = SpringApplication.run(SadapayAssignmentApplication::class.java)
        val activeProfiles = context.environment.activeProfiles
        Assertions.assertTrue(activeProfiles.containsAllSpecifiedElements(arrayOf("dev")))
    }
}