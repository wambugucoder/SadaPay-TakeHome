package com.example.sadapayassignment

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext

abstract class AbstractContextTest : BaseTest() {

    lateinit var context: ConfigurableApplicationContext

    @BeforeEach
    fun setup() {
        context = SpringApplication.run(
            SadapayAssignmentApplication::class.java,
            Fixtures.DISABLE_WEB_ENV_ARG
        )
    }

    @AfterEach
    fun tearDown() {
        context.close()
    }
}