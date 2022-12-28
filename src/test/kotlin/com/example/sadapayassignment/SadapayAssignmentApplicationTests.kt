package com.example.sadapayassignment

import com.example.sadapayassignment.Fixtures.DISABLE_WEB_ENV_ARG
import org.assertj.core.api.AssertionsForInterfaceTypes.*
import org.junit.jupiter.api.Test
import org.springframework.boot.SpringApplication

class SadapayAssignmentApplicationTests : BaseTest() {

	@Test
	fun contextLoads() {
		// Load the application context
		val context =
			SpringApplication.run(
			    SadapayAssignmentApplication::class.java,
			    DISABLE_WEB_ENV_ARG
			)

		// Verify that the context loads successfully
		assertThat(context).isNotNull
		assertThat(context.beanDefinitionCount).isGreaterThan(0)
	}
}